# BorderAPI 1.8.8 - 1.15.x [![Build Status](https://travis-ci.org/yannicklamprecht/WorldBorderAPI.svg?branch=master)](https://travis-ci.org/yannicklamprecht/WorldBorderAPI) [![](https://jitpack.io/v/yannicklamprecht/WorldBorderAPI.svg)](https://jitpack.io/#yannicklamprecht/WorldBorderAPI)


A world border api that allows it to define a world border per player

## Supported versions

- 1.8.8
- 1.11.2
- 1.12.2
- 1.13.2
- 1.14.4
- 1.15.1

## Usage


You just want to use the Plugin because it is a dependency of another Plugin you're using?
Have a look at [Getting started using it](#getting-started-using-it)


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

`WorldBorderAPI#setBorder(Player player, double size, int milliSeconds);`

Change the size of the players WorldBorder in a certain time.

`WorldBorderAPI#setBorder(Player player, double size, int time, TimeUnit timeUnit);`

Gets an instance to modify your stuff yourself

`WorldBorderAPI#getWorldBorder(Player p)`

Gets an instance of the global WorldBorder

`WorldBorderAPI#getWorldBorder`

## How to add with Maven

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


## More examples

See WorldBorderAPITestPlugin


## Getting started using it
From version 1.0.5 onwards the Jars in the release section are the following:

- API.jar _> The API non Maven/Gradle user can include to develop a plugin depending on this plugin
- WorldBorderAPITestPlugin.jar _> A plugin to test the functionallity of the WorldBorderPlugin (currently specific chat messages are used to trigger the different states)
- WorldBorderPlugin-[![](https://jitpack.io/v/yannicklamprecht/WorldBorderAPI.svg)](https://jitpack.io/#yannicklamprecht/WorldBorderAPI)-SNAPSHOT _> The plugin that should be dropped into the Pluginsfolder

