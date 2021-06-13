import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class BuildSpigotTask : DefaultTask() {


    @get:Input
    abstract val version: Property<String>
    @get:Input
    abstract val mojangMapped: Property<Boolean>


    @TaskAction
    fun build(){

        val additionalParams = mutableListOf(
            "java",
            "-jar",
            buildToolsJar.toFile().name,
            "--rev",
            version.get(),
            "--disable-java-check",
        )

        if(mojangMapped.get()) {
            additionalParams.addAll(listOf("--remapped"))
        }

        cmd(*additionalParams.toTypedArray(),
            directory = buildToolsPath.toFile(),
            printToStdout = true
        )
    }
}
