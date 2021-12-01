import de.chojo.Repo

plugins {
    `java-library`
    `maven-publish`
    id("io.papermc.paperweight.userdev") version "1.3.1-SNAPSHOT"
    id("xyz.jpenilla.run-paper") version "1.0.5"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.0"
    id("de.chojo.publishdata") version "1.0.1"
}



description = "plugin"

group = "com.github.yannicklamprecht"
version = "1.18.0-SNAPSHOT"

repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    paperDevBundle("1.18-R0.1-SNAPSHOT")
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

publishData {
    addRepo(Repo.main("", "https://eldonexus.de/repository/maven-releases/", false))
    useEldoNexusRepos()
    publishTask("jar")
    publishTask("sourcesJar")
    publishTask("javadocJar")
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
            url = uri(publishData.getRepository())
        }
    }
    publications.create<MavenPublication>("maven") {
        publishData.configurePublication(this)
    }
}


bukkit {
    name = "WorldBorderAPI"
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.STARTUP
    main = "com.github.yannicklamprecht.worldborder.plugin.WorldBorderPlugin"
    apiVersion = "1.18"
    authors = listOf("ysl3000")
}
