import org.gradle.api.Plugin
import org.gradle.api.Project
import java.net.URL
import java.nio.file.Path

open class WorldBorderApiToolsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.initWBTools()
    }
}

fun download(targetPath: Path, url: URL){
    if(!targetPath.toFile().exists()){
        targetPath.parent.toFile().mkdirs()
        targetPath.toFile().writeBytes(url.readBytes())
    }
}

fun Project.initWBTools() = run {
    project.tasks.register("setup") {
        group = taskGroup
        description = "Setups the tools used: SpecialSource, BuildTools"
        doFirst {
            tooling.toFile().mkdirs()
            download(buildToolsJar, buildToolsUrl)
            download(specialsourePath, specialsourceUrl)
        }
        finalizedBy(project.tasks.getByName("buildSpigot"))
    }

    project.tasks.register("cleanup"){
        group = taskGroup
        description = "Cleanup the tools used: SpecialSource, BuildTools"
        doLast {
            tooling.toFile().deleteRecursively()
        }
    }
}
