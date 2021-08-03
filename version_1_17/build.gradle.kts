
plugins {
    id("com.github.yannicklamprecht.java-conventions")
}

dependencies {
    compileOnly(project(":api"))
    compileOnly("org.spigotmc:spigot:1.17.1-R0.1-SNAPSHOT")
}

description = "version_1_17"

