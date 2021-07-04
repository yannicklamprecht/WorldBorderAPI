
plugins {
    id("com.github.yannicklamprecht.java-conventions")
    id("com.github.yannicklamprecht.spigot.tools") version "1.0.1"
}

dependencies {
    compileOnly(projects.api)
    compileOnly("org.spigotmc:spigot:1.17-R0.1-SNAPSHOT:remapped-mojang")
}

description = "version_1_17"



tasks {
    spigotTools {
        mojangMapped.set(true)
        version.set("1.17")
        outputClassifier.set("spigot-mapped")
    }
}
