import java.nio.charset.StandardCharsets

plugins {
    `java-library`
    `maven-publish`
    id("xyz.jpenilla.run-paper") version "3.1.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}



description = "plugin"

group = "com.github.yannicklamprecht"
version = file("version.txt").readText(StandardCharsets.UTF_8).trim()

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21.11-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

tasks {

    compileJava {
        options.encoding = Charsets.UTF_8.name()
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
        artifact(tasks.named("jar").get()) {
            classifier = "dev"
        }

        artifact(tasks.named("sourcesJar").get()) {
            classifier = "sources"
        }

        artifact(tasks.named("javadocJar").get()) {
            classifier = "javadoc"
        }
    }
}

bukkit {
    name = "WorldBorderAPI"
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.STARTUP
    main = "com.github.yannicklamprecht.worldborder.plugin.WorldBorderPlugin"
    apiVersion = "1.20"
    authors = listOf("ysl3000")
    foliaSupported = true
}
