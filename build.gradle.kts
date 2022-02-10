plugins {
    `java-library`
    `maven-publish`
    id("io.papermc.paperweight.userdev") version "1.3.5-SNAPSHOT"
    id("xyz.jpenilla.run-paper") version "1.0.6"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}



description = "plugin"

group = "com.github.yannicklamprecht"
version = "1.181.1"

repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    paperDevBundle("1.18.1-R0.1-SNAPSHOT")
}

tasks {
    // Run reobfJar on build
    build {
        dependsOn(reobfJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    repositories {
        maven {
            authentication {
                credentials(PasswordCredentials::class)
            }
            name = "eldonexus"
            url = uri(
                if (project.version.toString()
                        .endsWith("SNAPSHOT")
                ) "https://eldonexus.de/repository/maven-snapshots/" else "https://eldonexus.de/repository/maven-releases/"
            )
        }
    }
    publications.create<MavenPublication>("maven") {
        from(components["java"])
        artifact(tasks.reobfJar)
    }
}


bukkit {
    name = "WorldBorderAPI"
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.STARTUP
    main = "com.github.yannicklamprecht.worldborder.plugin.WorldBorderPlugin"
    apiVersion = "1.18"
    authors = listOf("ysl3000")
}
