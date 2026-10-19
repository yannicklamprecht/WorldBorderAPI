package com.github.yannicklamprecht.worldborder.commands;

import com.github.yannicklamprecht.worldborder.api.IWorldBorder;
import com.github.yannicklamprecht.worldborder.api.Position;
import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.BiConsumer;
import java.util.concurrent.TimeUnit;

import static io.papermc.paper.command.brigadier.Commands.argument;
import static io.papermc.paper.command.brigadier.Commands.literal;

public final class WorldBorderCommandsPlugin extends JavaPlugin {
    private WorldBorderApi worldBorderApi;

    @Override
    public void onEnable() {
        worldBorderApi = getServer().getServicesManager().load(WorldBorderApi.class);
        if (worldBorderApi == null) {
            getLogger().severe("WorldBorderAPI service is unavailable; disabling command plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event ->
                event.registrar().register(createCommand(), "Control a player's WorldBorderAPI border"));
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event ->
                event.registrar().register(createPlayerCommand(), "Test WorldBorderAPI on yourself", java.util.List.of("worldborderapitest")));
    }

    private com.mojang.brigadier.tree.LiteralCommandNode<CommandSourceStack> createPlayerCommand() {
        return literal("wbat")
                .requires(source -> source.getSender() instanceof Player)
                .then(literal("reset").executes(context -> {
                    Player player = (Player) context.getSource().getSender();
                    worldBorderApi.resetWorldBorderToGlobal(player);
                    player.sendMessage("Reset your border to the global world border.");
                    return 1;
                }))
                .then(literal("redscreen")
                        .executes(context -> redScreen(context.getSource(), 30))
                        .then(argument("seconds", IntegerArgumentType.integer(0))
                                .executes(context -> redScreen(context.getSource(), IntegerArgumentType.getInteger(context, "seconds")))))
                .then(literal("set")
                        .then(argument("size", DoubleArgumentType.doubleArg(0))
                                .executes(context -> {
                                    Player player = (Player) context.getSource().getSender();
                                    Location location = player.getLocation();
                                    worldBorderApi.setBorder(player, DoubleArgumentType.getDouble(context, "size"), location);
                                    player.sendMessage("Set your border size to " + DoubleArgumentType.getDouble(context, "size") + ".");
                                    return 1;
                                })
                                .then(literal("over")
                                        .then(argument("seconds", IntegerArgumentType.integer(0))
                                                .executes(context -> {
                                                    Player player = (Player) context.getSource().getSender();
                                                    int seconds = IntegerArgumentType.getInteger(context, "seconds");
                                                    worldBorderApi.setBorder(player, DoubleArgumentType.getDouble(context, "size"), seconds, TimeUnit.SECONDS);
                                                    player.sendMessage("Animating your border to the requested size over " + seconds + " seconds.");
                                                    return 1;
                                                })))
                                .then(literal("at")
                                        .then(argument("x", DoubleArgumentType.doubleArg())
                                                .then(argument("z", DoubleArgumentType.doubleArg())
                                                        .executes(context -> {
                                                            Player player = (Player) context.getSource().getSender();
                                                            worldBorderApi.setBorder(player, DoubleArgumentType.getDouble(context, "size"),
                                                                    new Position(DoubleArgumentType.getDouble(context, "x"), DoubleArgumentType.getDouble(context, "z")));
                                                            player.sendMessage("Set your border center and size.");
                                                            return 1;
                                                        }))))))
                .then(literal("lerp")
                        .then(argument("old-size", DoubleArgumentType.doubleArg(0))
                                .then(argument("new-size", DoubleArgumentType.doubleArg(0))
                                        .executes(context -> lerp(context.getSource(),
                                                DoubleArgumentType.getDouble(context, "old-size"),
                                                DoubleArgumentType.getDouble(context, "new-size"), 10))
                                        .then(argument("seconds", IntegerArgumentType.integer(0))
                                                .executes(context -> lerp(context.getSource(),
                                                        DoubleArgumentType.getDouble(context, "old-size"),
                                                        DoubleArgumentType.getDouble(context, "new-size"),
                                                        IntegerArgumentType.getInteger(context, "seconds")))))))
                .then(literal("green").executes(context -> animateDemo(context.getSource(), 2)))
                .then(literal("red").executes(context -> animateDemo(context.getSource(), -2)))
                .then(literal("print").executes(context -> printBorder(context.getSource())))
                .build();
    }

    private int redScreen(CommandSourceStack source, int seconds) {
        Player player = (Player) source.getSender();
        worldBorderApi.sendRedScreenForSeconds(player, seconds, this);
        player.sendMessage("Showing the red border warning for " + seconds + " seconds.");
        return 1;
    }

    private int lerp(CommandSourceStack source, double oldSize, double newSize, int seconds) {
        Player player = (Player) source.getSender();
        IWorldBorder border = worldBorderApi.getWorldBorder(player);
        border.lerp(oldSize, newSize, seconds);
        border.send(player, com.github.yannicklamprecht.worldborder.api.WorldBorderAction.INITIALIZE);
        player.sendMessage("Animating your border from " + oldSize + " to " + newSize + " over " + seconds + " seconds.");
        return 1;
    }

    private int animateDemo(CommandSourceStack source, double delta) {
        Player player = (Player) source.getSender();
        IWorldBorder border = worldBorderApi.getWorldBorder(player);
        double current = border.size();
        border.setCenter(Position.of(player.getLocation()));
        border.lerp(current, current + delta, 10);
        border.send(player, com.github.yannicklamprecht.worldborder.api.WorldBorderAction.INITIALIZE);
        player.sendMessage("Animating your border by " + delta + " over 10 seconds.");
        return 1;
    }

    private int printBorder(CommandSourceStack source) {
        Player player = (Player) source.getSender();
        IWorldBorder border = worldBorderApi.getWorldBorder(player);
        Position center = border.center();
        player.sendMessage("size=" + border.size() + ", center=" + center.x() + ", " + center.z()
                + ", damageBuffer=" + border.damageBufferInBlocks()
                + ", warningDistance=" + border.warningDistanceInBlocks()
                + ", warningTime=" + border.warningTimerInSeconds());
        return 1;
    }

    private com.mojang.brigadier.tree.LiteralCommandNode<CommandSourceStack> createCommand() {
        return literal("wbapi")
                .requires(source -> source.getSender().hasPermission("worldborderapi.commands"))
                .then(literal("show")
                        .then(argument("player", ArgumentTypes.player())
                                .executes(context -> {
                                    Player player = player(context.getSource(), context.getArgument("player", PlayerSelectorArgumentResolver.class));
                                    IWorldBorder border = worldBorderApi.getWorldBorder(player);
                                    Position center = border.center();
                                    context.getSource().getSender().sendMessage("Border for " + player.getName()
                                            + ": size=" + border.size() + ", center=" + center.x() + ", " + center.z()
                                            + ", damageBuffer=" + border.damageBufferInBlocks()
                                            + ", warningDistance=" + border.warningDistanceInBlocks()
                                            + ", warningTime=" + border.warningTimerInSeconds());
                                    return 1;
                                })))
                .then(literal("set")
                        .then(argument("player", ArgumentTypes.player())
                                .then(literal("center")
                                        .then(argument("x", DoubleArgumentType.doubleArg())
                                                .then(argument("z", DoubleArgumentType.doubleArg())
                                                        .executes(context -> update(context.getSource(), context.getArgument("player", PlayerSelectorArgumentResolver.class), "center", border -> border.setCenter(new Position(DoubleArgumentType.getDouble(context, "x"), DoubleArgumentType.getDouble(context, "z"))))))))
                                .then(numericSetting("size", "size", DoubleArgumentType.doubleArg(0), IWorldBorder::setSize))
                                .then(numericSetting("damage-buffer", "damageBuffer", DoubleArgumentType.doubleArg(0), IWorldBorder::setDamageBufferInBlocks))
                                .then(numericSetting("warning-distance", "warningDistance", IntegerArgumentType.integer(0), (border, value) -> border.setWarningDistanceInBlocks(value.intValue())))
                                .then(numericSetting("warning-time", "warningTime", IntegerArgumentType.integer(0), (border, value) -> border.setWarningTimeInSeconds(value.intValue())))))
                .then(literal("reset")
                        .then(argument("player", ArgumentTypes.player())
                                .executes(context -> {
                                    Player player = player(context.getSource(), context.getArgument("player", PlayerSelectorArgumentResolver.class));
                                    worldBorderApi.resetWorldBorderToGlobal(player);
                                    context.getSource().getSender().sendMessage("Reset " + player.getName() + " to the global world border.");
                                    return 1;
                                })))
                .build();
    }

    private com.mojang.brigadier.builder.LiteralArgumentBuilder<CommandSourceStack> numericSetting(
            String literal, String argumentName, com.mojang.brigadier.arguments.ArgumentType<? extends Number> type,
            BiConsumer<IWorldBorder, Double> setter) {
        return literal(literal)
                .then(argument(argumentName, type)
                        .executes(context -> {
                            Number number = context.getArgument(argumentName, Number.class);
                            return update(context.getSource(), context.getArgument("player", PlayerSelectorArgumentResolver.class), literal,
                                    border -> setter.accept(border, number.doubleValue()));
                        }));
    }

    private int update(CommandSourceStack source, PlayerSelectorArgumentResolver resolver, String setting,
                       java.util.function.Consumer<IWorldBorder> change) throws CommandSyntaxException {
        Player player = player(source, resolver);
        IWorldBorder border = worldBorderApi.getWorldBorder(player);
        change.accept(border);
        border.send(player, com.github.yannicklamprecht.worldborder.api.WorldBorderAction.INITIALIZE);
        source.getSender().sendMessage("Updated " + setting + " for " + player.getName() + ".");
        return 1;
    }

    private Player player(CommandSourceStack source, PlayerSelectorArgumentResolver resolver) throws CommandSyntaxException {
        return resolver.resolve(source).getFirst();
    }
}
