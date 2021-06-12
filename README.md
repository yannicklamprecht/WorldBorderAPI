# BorderAPI 1.14.4 - 1.17.0 [![Build Status](https://travis-ci.org/yannicklamprecht/WorldBorderAPI.svg?branch=master)](https://travis-ci.org/yannicklamprecht/WorldBorderAPI) [![](https://jitpack.io/v/yannicklamprecht/WorldBorderAPI.svg)](https://jitpack.io/#yannicklamprecht/WorldBorderAPI)

A world border api that allows it to define a world border per player

---
## Available versions

### Working just no bugfixes

Legacy: [Artifacts](https://github.com/yannicklamprecht/WorldBorderAPI/releases/tag/1.15.2)
- 1.8.8
- 1.11.2
- 1.12.2
- 1.13.2
- 1.14.4
- 1.15.2

### Working and Bugfixes

#### Release

Built with Java 8: [Artifacts](https://github.com/yannicklamprecht/WorldBorderAPI/releases/tag/1.165.0)
- 1.14.4
- 1.15.2
- 1.16.4/5

#### Snapshot

Built with Java 16: [Not yet available, <span style="text-decoration:underline">Also available as Mojang mapped artifact</span>]()
- 1.16.4/5
- 1.17.0

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


up to 1.165.0 but deprecated (marked for removal with 1.190.0) replacement see below
```java
WorldBorderAPI worldBorderAPI = BorderAPI.getApi();
```

from 1.170.0 (not released comes with 1.17)
```java
RegisteredServiceProvider<WorldBorderApi> worldBorderApiRegisteredServiceProvider = getServer().getServicesManager().getRegistration(WorldBorderApi.class);

if (worldBorderApiRegisteredServiceProvider == null) {
    getLogger().info("API not found");
    getServer().getPluginManager().disablePlugin(this);
    return;
}

worldBorderApi = worldBorderApiRegisteredServiceProvider.getProvider();
```

Sends a red screen for a certain time for player.


```java
WorldBorderAPI#sendRedScreenForSeconds(Player player, int timeSeconds, JavaPlugin javaPlugin);
```

Change the size per player.

```java
WorldBorderAPI#setBorder(Player player, double size);
```

Resets the player world border to the global one

```java
WorldBorderAPI#resetWorldBorderToGlobal(Player player);
```

Sets the location,size of the players WorldBorder.

```java
WorldBorderAPI#setBorder(Player player, double size, Location location);
```

Change the size of the players WorldBorder in a certain time.

```java
WorldBorderAPI#setBorder(Player player, double size, int milliSeconds);
```

Change the size of the players WorldBorder in a certain time.

```java
WorldBorderAPI#setBorder(Player player, double size, int time, TimeUnit timeUnit);
```

Gets an instance to modify your stuff yourself

```java
WorldBorderAPI#getWorldBorder(Player p);
```

Gets an instance of the global WorldBorder

```java
WorldBorderAPI#getWorldBorder()
```

### How to add with Maven

```xml
<repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
</repositories>
 ```
 
 ```xml
<dependency>
   <groupId>com.github.yannicklamprecht.WorldBorderAPI</groupId>
   <artifactId>API</artifactId>
   <version>1.165.0</version>
</dependency>
```

### How to add with Gradle KTS

```kotlin
repositories {
    maven {
        name = "jitpack.io"
        url = uri("https://jitpack.io")
    }    
}
```

````kotlin
dependencies {
    compileOnly("com.github.yannicklamprecht.WorldBorderAPI:API:1.165.0")
}
````


### More examples

See WorldBorderAPITestPlugin


