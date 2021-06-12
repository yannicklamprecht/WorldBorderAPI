import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.name

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
            buildToolsPath.toFile().name,
            "--rev",
            version.get(),
            "--disable-java-check",
        )

        if(mojangMapped.get()) {
            additionalParams.addAll(listOf("--remapped", "remapped-mojang"))

        }

        cmd(*additionalParams.toTypedArray(),
            directory = buildToolsPath.parent.toFile(),
            printToStdout = true
        )
    }
}
