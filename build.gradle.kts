import java.nio.charset.StandardCharsets

plugins {
    `java-library`
    `maven-publish`
    id("io.papermc.paperweight.userdev") version "1.5.1"
    id("xyz.jpenilla.run-paper") version "2.0.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
}



description = "plugin"

group = "com.github.yannicklamprecht"
version = file("version.txt").readText(StandardCharsets.UTF_8).trim()

repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    paperDevBundle("1.19.3-R0.1-SNAPSHOT")
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

fun addReobfTo(target: NamedDomainObjectProvider<Configuration>, classifier: String? = null) {
    target.get().let {
        it.outgoing.artifact(tasks.reobfJar.get().outputJar) {
            this.classifier = classifier
        }
        (components["java"] as AdhocComponentWithVariants).addVariantsFromConfiguration(it) {}
    }
}

addReobfTo(configurations.apiElements)
addReobfTo(configurations.runtimeElements)

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
    }
}

bukkit {
    name = "WorldBorderAPI"
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.STARTUP
    main = "com.github.yannicklamprecht.worldborder.plugin.WorldBorderPlugin"
    apiVersion = "1.19"
    authors = listOf("ysl3000")
}
