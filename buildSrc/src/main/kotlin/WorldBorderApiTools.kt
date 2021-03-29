import org.gradle.api.Plugin
import org.gradle.api.Project

open class WorldBorderApiToolsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.initWBTools()
    }
}

fun Project.initWBTools() = run {
    project.tasks.register("downloadBuildTools") {
        group = taskGroup
        onlyIf {
            !buildToolsDir.exists() ||
                    !buildToolsFile.exists()
        }
        doLast {
            buildToolsDir.mkdirs()
            buildToolsDir.resolve(buildToolsFile).writeBytes(buildToolsUrl.readBytes())
        }
    }.name
}
