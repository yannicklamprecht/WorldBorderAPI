# BorderAPI 1.8.x <-> 1.13.x

### Download Plugin

Check the plugin releases [here](https://github.com/astromc/BorderAPI/releases).

### What versions are supported?

- [x] 1.8.8
- [ ] 1.9.x
- [x] 1.11.x
- [x] 1.12.x
- [x] 1.13.x

### Example

The following will send a 16 block radius border around a player

```java
Player player = Bukkit.getPlayer("Notch");
BorderAPI.setBorder(player, 16.0D, player.getLocation());
```

### Methods

These methods are available from the static BorderAPI class.

| Method                                | Description                                                   |
| :------------------------------------ | :------------------------------------------------------------ |
| `sendRedBorder(player)`               | Make the players creen red for 10 seconds.                    |
| `sendRedBorder(player, seconds)`      | Make the players screen red for n seconds.                    |
| `setBorder(player, radius)`           | Send the border centered at the world spawn.                  |
| `setBorder(player, radius, location)` | Send the border centered at the provided location.            |
| `setBorder(player, radius, seconds)`  | Send the border centered at world spawn.                      |
| `setBorder(radius)`                   | Send a border to all online players, centered at world spawn. |

### Contribute

Pull requests are encouraged.
