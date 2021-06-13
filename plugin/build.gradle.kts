
plugins {
    id("com.github.yannicklamprecht.java-conventions")
    id("com.github.johnrengelman.shadow") version shadowVersion
}

dependencies {
    implementation(projects.api)
    implementation(projects.version116)
    implementation(projects.version117)
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
}

description = "plugin"

tasks {
    shadowJar {
        archiveBaseName.set("worldborderapiplugin")
    }
    processResources {
        from(sourceSets.main.get().resources.srcDirs) {
            filesMatching("plugin.yml") {
                expand(
                    "version" to project.version
                )
            }
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }
    }
}
