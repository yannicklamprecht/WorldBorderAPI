import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

open class BuildSpigotTask : DefaultTask() {

    @Input
    lateinit var version: String

    @TaskAction
    fun build(){
        cmd(
            "java",
            "-jar",
            buildToolsFile.name,
            "--rev",
            version,
            directory = buildToolsDir,
            printToStdout = true
        )
    }
}
