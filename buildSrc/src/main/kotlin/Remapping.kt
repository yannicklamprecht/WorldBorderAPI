import org.gradle.api.Project
import java.io.File
import java.nio.file.Path

fun Project.remap(
    vararg additionalParameters: String,
    inputPath: File,
    outputPath: File,
    mapEnding: String,
    spigotVersion: String
) {
    project.parent?.let {
        val mutableArguments = mutableListOf(
            "java",
            "-jar",
            it.projectDir.toPath().resolve(specialsourePath).toString(),
            "-i",
            inputPath.absolutePath,
            "-o",
            outputPath.absolutePath,
            "-m",
            minecraftFile(spigotVersion, "-${mapEnding}").toString()
        )
        mutableArguments.addAll(additionalParameters)
        cmd(
            *mutableArguments.toTypedArray(),
            directory = buildToolsPath.toFile(),
            printToStdout = true
        )
    }
}


fun Project.remapClasses(
    inputPath: File,
    outputPath: File,
    nmsVersion: String,
    classifier: String
) {
    project.parent?.let {
        val mutableArguments = mutableListOf(
            "java",
            "-jar",
            it.projectDir.toPath().resolve(specialsourePath).toString(),
            "-i",
            inputPath.absolutePath,
            "-o",
            outputPath.absolutePath,
            "-m",
            Path.of(
                "BuildData",
                "mappings",
                "bukkit-${nmsVersion}${classifier}"
            ).toString()
        )
        cmd(
            *mutableArguments.toTypedArray(),
            directory = buildToolsPath.toFile(),
            printToStdout = true
        )
    }
}


private fun Project.specialSource(name: String): String {
    return projectDir.parentFile.resolve(buildToolsPath.resolve(Path.of("BuildData", "bin", name)).toFile()).toString();
}

private fun spigotGroupMavenRoot(): Path {
    return Path.of(System.getProperty("user.home"), ".m2", "repository", "org", "spigotmc")
}

private fun minecraftFile(spigotRefVersion: String, fileClassifier: String = ""): Path {
    return spigotGroupMavenRoot().resolve(
        Path.of(
            "minecraft-server",
            spigotRefVersion,
            "minecraft-server-${spigotRefVersion}${fileClassifier}"
        )
    )
}
