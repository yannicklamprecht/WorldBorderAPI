import java.io.File
import java.net.URL

val buildToolsDir = File("buildtools")
val buildToolsFile = File("BuildTools.jar")
val buildToolsUrl =
    URL("https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar")
const val taskGroup = "worldborderapitools"

val specialSourceDir = File("specialsource")
val specialsoureFile = File("SpecialSource.jar")
val specialsourceUrl = URL("https://repo.maven.apache.org/maven2/net/md-5/SpecialSource/1.10.0/SpecialSource-1.10.0-shaded.jar")

const val shadowVersion = "7.0.0"
