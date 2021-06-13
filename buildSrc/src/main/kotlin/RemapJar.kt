import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Path

@CacheableTask
abstract class RemapJar : DefaultTask() {

    @get:Input
    abstract val spigotVersion: Property<String>

    @get:Input
    @get:Optional
    abstract val inputClassifier: Property<String>

    @get:Input
    @get:Optional
    abstract val outputClassifier: Property<String>

    private val libDir = project.buildDir.resolve("libs")

    @TaskAction
    fun remapJar() {
        val tempFile = libDir.resolve("temp.jar")
        remap(
            "--reverse",
            inputPath = libDir.resolve(
                "${project.description}-${project.version}${
                    inputClassifier.map { "-${it}" }.getOrElse("")
                }.jar"
            ),
            outputPath = tempFile,
            mapEnding = "maps-mojang.txt",
            spigotVersion = spigotVersion.get()
        )
        logger.lifecycle("Remap from Obf to Spigot")
        remap(
            inputPath = tempFile,
            outputPath = libDir.resolve(
                "${project.description}-${project.version}${
                    outputClassifier.map { "-${it}" }.getOrElse("")
                }.jar"
            ),
            mapEnding = "maps-spigot.csrg",
            spigotVersion = spigotVersion.get()
        )
        tempFile.delete()
    }

    private fun remap(
        vararg additionalParameters: String,
        inputPath: File,
        outputPath: File,
        mapEnding: String,
        spigotVersion: String
    ) {

        val projectDir = project.parent?.project?.projectDir ?: project.projectDir
        val mutableArguments = mutableListOf(
            "java",
            "-jar",
            projectDir.toPath().resolve(specialsourePath).toString(),
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


    fun remapClasses(
        inputPath: File,
        outputPath: File,
        nmsVersion: String,
        classifier: String
    ) {
        val projectDir = project.parent?.project?.projectDir ?: project.projectDir

        val mutableArguments = mutableListOf(
            "java",
            "-jar",
            projectDir.toPath().resolve(specialsourePath).toString(),
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
}
