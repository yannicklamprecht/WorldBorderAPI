
plugins {
    id("com.github.yannicklamprecht.java-conventions")
}

dependencies {
    compileOnly(project(":api"))
    compileOnly("org.spigotmc:spigot:1.16.5-R0.1-SNAPSHOT")
}

description = "version_1_16"

