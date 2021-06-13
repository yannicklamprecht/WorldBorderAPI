
plugins {
    id("com.github.yannicklamprecht.java-conventions")
    id("com.github.johnrengelman.shadow") version shadowVersion
}

dependencies {
    implementation(projects.api)
    implementation(projects.version116)
    implementation(projects.version117)
    compileOnly("org.spigotmc:spigot-api:1.14.4-R0.1-SNAPSHOT")
}

description = "plugin"

tasks.shadowJar {
    archiveClassifier.set("mojang")
}

fun String.asClassifiedJar(): File {
    return project.tasks.shadowJar.get().let {
        return@let it.archiveFile.get().asFile.parentFile.resolve("${it.archiveBaseName.get()}-${project.version}${this.let { classifier -> "-$classifier" }}.jar")
    }
}

val remapJar: Task by tasks.creating {
    group = taskGroup
    description = "Remaps the Jar to use spigot mappings"
    doLast {
        logger.lifecycle("Remap from Mojang to Obf")
        remap(
            "--reverse",
            inputPath = "mojang".asClassifiedJar(),
            outputPath = "obf".asClassifiedJar(),
            mapEnding = "maps-mojang.txt",
            spigotVersion = "1.17-R0.1-SNAPSHOT"
        )
        logger.lifecycle("Remap from Obf to Spigot")
        remap(
            inputPath = "obf".asClassifiedJar(),
            outputPath = "spigot".asClassifiedJar(),
            mapEnding = "maps-spigot.csrg",
            spigotVersion = "1.17-R0.1-SNAPSHOT"
        )
        "obf".asClassifiedJar().delete()
    }
}
