/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("com.github.yannicklamprecht.java-conventions")
    id("com.github.johnrengelman.shadow") version shadowVersion
}

dependencies {
    implementation("co.aikar:acf-bukkit:0.5.0-SNAPSHOT")
    compileOnly("org.spigotmc:spigot-api:1.14.1-R0.1-SNAPSHOT")
    compileOnly(project(":api"))
}

description = "testplugin"