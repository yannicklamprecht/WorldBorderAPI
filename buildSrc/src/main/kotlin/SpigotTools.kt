import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar
import java.net.URL
import java.nio.file.Path

open class SpigotTools : Plugin<Project> {

    private fun download(targetPath: Path, url: URL) {
        if (!targetPath.toFile().exists()) {
            targetPath.parent.toFile().mkdirs()
            targetPath.toFile().writeBytes(url.readBytes())
        }
    }

    override fun apply(target: Project) {
        target.tasks.register("setup") {
            group = taskGroup
            description = "Setups the tools used: SpecialSource, BuildTools"
            doFirst {
                tooling.toFile().mkdirs()
                download(buildToolsJar, buildToolsUrl)
                download(specialsourePath, specialsourceUrl)
            }
            finalizedBy(project.tasks.withType(BuildSpigotTask::class.java))
        }

        target.tasks.register("cleanup") {
            group = taskGroup
            description = "Cleanup the tools used: SpecialSource, BuildTools"
            doLast {
                tooling.toFile().deleteRecursively()
            }
        }

        target.tasks.withType(Jar::class.java) {
            finalizedBy(target.tasks.withType(RemapJar::class.java))
        }
    }
}


