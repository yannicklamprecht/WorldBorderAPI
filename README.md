# BorderAPI 1.8.8 - 1.14.x

A world border api that allows it to define a world border per player

## Supported versions

- 1.8.8
- 1.11.2
- 1.12.2
- 1.13.2
- 1.14.1 (later on only 1.14.n)

## Usage


Obtain the API

`WorldBorderAPI worldBorderAPI = BorderAPI.getApi();`

Sends a red screen for a certain time for player.

`WorldBorderAPI#sendRedScreenForSeconds(Player player, int timeSeconds, JavaPlugin javaPlugin);`

Change the size per player.

`WorldBorderAPI#setBorder(Player player, double size);`

Resets the player world border to the global one

`WorldBorderAPI#resetWorldBorderToGlobal(Player player);`

Sets the location,size of the players WorldBorder.

`WorldBorderAPI#setBorder(Player player, double size, Location location);`

Change the size of the players WorldBorder in a certain time.

`WorldBorderAPI#setBorder(Player player, double size, int seconds);`

Gets an instance to modify your stuff yourself

`WorldBorderAPI#getWorldBorder(Player p)`

Gets an instance of the global WorldBorder

`WorldBorderAPI#getWorldBorder`
