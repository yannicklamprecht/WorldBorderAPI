# BorderAPI [![Build Status](https://travis-ci.org/yannicklamprecht/WorldBorderAPI.svg?branch=master)](https://travis-ci.org/yannicklamprecht/WorldBorderAPI)

A world border api that allows it to define a world border per player

---
## Available versions

### Release

Built with Java 16
- 1.17.1

### Snapshot

Built with Java 17
- 1.18.0-rc3 https://github.com/yannicklamprecht/WorldBorderAPI/releases/tag/1.18.0-rc3



--- 

## Legacy

### Working just no bugfixes

Legacy: [Artifacts](https://github.com/yannicklamprecht/WorldBorderAPI/releases/tag/1.15.2)
- 1.8.8, 1.11.2, 1.12.2, 1.13.2, 1.14.4, 1.15.2

Built with Java 8: [Artifacts](https://github.com/yannicklamprecht/WorldBorderAPI/releases/tag/1.165.0)
- 1.14.4, 1.15.2, 1.16.4/5

---

## Usage

You just want to use the Plugin because it is a dependency of another Plugin you're using?
Have a look at the release section. The right jar is named like this `worldborderapiplugin-1.170.0-SNAPSHOT`

You're a developer and want to use this as a dependency for your plugin? [See](#obtain-the-api)

---
## Obtain the API

You can either develop using the `api.jar` from the release section or by using Maven/Gradle [See](#how-to-add-it-with-maven)


Up to 1.165.0 but deprecated (marked for removal with 1.190.0) replacement see below
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
            <id>eldonexus</id>
            <url>https://eldonexus.de/repository/maven-releases/</url>
        </repository>
</repositories>
 ```
 
 ```xml

<dependency>
  <groupId>com.github.yannicklamprecht.worldborderapi</groupId>
  <artifactId>api</artifactId>
  <version>1.170.0</version>
  <scope>provided</scope>
</dependency>
```

### How to add with Gradle KTS

```kotlin
repositories {
    maven {
        name = "eldonexus"
        url = uri("https://eldonexus.de/repository/maven-releases/")
    }    
}
```

````kotlin
dependencies {
    compileOnly("com.github.yannicklamprecht.worldborderapi:api:1.170.0")
}
````


### More examples

See WorldBorderAPITestPlugin


