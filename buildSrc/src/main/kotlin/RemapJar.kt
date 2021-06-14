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

    fun artifact(): File {
        return libDir.resolve(
            "${project.description}-${project.version}${
                outputClassifier.map { "-${it}" }.getOrElse("")
            }.jar")
    }

    fun inputArtifact(): File {
        return libDir.resolve(
            "${project.description}-${project.version}${
                inputClassifier.map { "-${it}" }.getOrElse("")
            }.jar"
        )
    }

    private val libDir = project.buildDir.resolve("libs")

    @TaskAction
    fun remapJar() {
        val tempFile = libDir.resolve("temp.jar")
        remap(
            "--reverse",
            inputPath = inputArtifact(),
            outputPath = tempFile,
            mapEnding = "maps-mojang.txt",
            spigotVersion = spigotVersion.get(),
            obf = false
        )
        logger.lifecycle("Remap from Obf to Spigot")
        remap(
            inputPath = tempFile,
            outputPath = artifact(),
            mapEnding = "maps-spigot.csrg",
            spigotVersion = spigotVersion.get(),
            obf = true
        )
        tempFile.delete()
    }

    private fun remap(
        vararg additionalParameters: String,
        inputPath: File,
        outputPath: File,
        mapEnding: String,
        spigotVersion: String,
        obf: Boolean
    ) {

        val projectDir = project.parent?.project?.projectDir ?: project.projectDir
        val mutableArguments = mutableListOf(
            "java",
            "-cp",
            "${projectDir.toPath().resolve(specialsourePath)}:${spigotGroupMavenRoot().resolve(Path.of("spigot",
                spigotVersion, "spigot-${spigotVersion}-remapped-${if(obf) "obf" else "mojang"}.jar"))}",
            "net.md_5.specialsource.SpecialSource",
            "--live",
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
