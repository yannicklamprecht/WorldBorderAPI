import org.gradle.api.Project
import java.io.File

fun Project.remap(vararg additionalParameters: String, inputPath: File, outputPath: File, mapEnding: String) {
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
            System.getProperty("user.home") + "/.m2/repository/org/spigotmc/minecraft-server/1.17-R0.1-SNAPSHOT/minecraft-server-1.17-R0.1-SNAPSHOT-${mapEnding}"
        )
        mutableArguments.addAll(additionalParameters)
        cmd(
            *mutableArguments.toTypedArray(),
            directory = buildToolsPath.parent.toFile(),
            printToStdout = true
        )
    }
}


