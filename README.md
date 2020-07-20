# BorderAPI 1.14.4 - 1.16.1 [![Build Status](https://travis-ci.org/yannicklamprecht/WorldBorderAPI.svg?branch=master)](https://travis-ci.org/yannicklamprecht/WorldBorderAPI) [![](https://jitpack.io/v/yannicklamprecht/WorldBorderAPI.svg)](https://jitpack.io/#yannicklamprecht/WorldBorderAPI)

A world border api that allows it to define a world border per player

---
## Supported versions

- 1.14.4
- 1.15.2
- 1.16.1

---

## Usage

You just want to use the Plugin because it is a dependency of another Plugin you're using?
Have a look at the release section. The right jar is named like this `WorldBorderPlugin-1.0.4-SNAPSHOT`

You're a developer and want to use this as a dependency for your plugin? [See](#obtain-the-api)

From version 1.0.5 onwards the following jars are available in the release section:
- API.jar _> The API non Maven/Gradle user can include to develop a plugin depending on this plugin
- WorldBorderAPITestPlugin.jar _> A plugin to test the functionallity of the WorldBorderPlugin (currently specific chat messages are used to trigger the different states)
 - WorldBorderPlugin-1.0.4-SNAPSHOT.jar _> The plugin that should be dropped into the Pluginsfolder

---
## Obtain the API

You can either develop using the `API.jar` from the release section or by using Maven/Gradle [See](#how-to-add-it-with-maven)


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

`WorldBorderAPI#setBorder(Player player, double size, int milliSeconds);`

Change the size of the players WorldBorder in a certain time.

`WorldBorderAPI#setBorder(Player player, double size, int time, TimeUnit timeUnit);`

Gets an instance to modify your stuff yourself

`WorldBorderAPI#getWorldBorder(Player p)`

Gets an instance of the global WorldBorder

`WorldBorderAPI#getWorldBorder`

### How to add with Maven

```
<repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
</repositories>
 ```
 
 ```
<dependency>
   <groupId>com.github.yannicklamprecht.WorldBorderAPI</groupId>
   <artifactId>API</artifactId>
   <version>1.0.3</version>
</dependency>

```
Replace `1.0.3` with the current release version found here [![](https://jitpack.io/v/yannicklamprecht/WorldBorderAPI.svg)](https://jitpack.io/#yannicklamprecht/WorldBorderAPI)

### More examples

See WorldBorderAPITestPlugin


