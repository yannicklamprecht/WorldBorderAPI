
plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.1.11"
    id("xyz.jpenilla.run-paper") version "1.0.4-SNAPSHOT"
    id("net.minecrell.plugin-yml.bukkit") version "0.4.0"
}



description = "plugin"

group = "com.github.yannicklamprecht"
version = "1.171.0"

repositories {
    maven {
        name = "eldonexus"
        url = uri("https://eldonexus.de/repository/maven-snapshots/")
        // url = uri("https://eldonexus.de/repository/maven-releases/")
    }
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    paperDevBundle("1.17.1-R0.1-SNAPSHOT")
}

tasks {
    // Run reobfJar on build
    build {
        dependsOn(reobfJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(16)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}


bukkit {
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.STARTUP
    main = "com.github.yannicklamprecht.worldborder.plugin.WorldBorderPlugin"
    apiVersion = "1.17"
    authors = listOf("ysl3000")
}