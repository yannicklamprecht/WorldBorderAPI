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

    project.tasks.register("downloadSpecialsource"){
        group = taskGroup
        onlyIf {
            !specialSourceDir.exists() ||
                    !specialsoureFile.exists()
        }
        doLast {
            specialSourceDir.mkdirs()
            specialSourceDir.resolve(specialsoureFile).writeBytes(specialsourceUrl.readBytes())
        }
    }.name
}
