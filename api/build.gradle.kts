plugins {
    id("com.github.yannicklamprecht.java-conventions")
    `maven-publish`
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
    javadoc {
        options.encoding = "UTF-8"
        source = sourceSets.main.get().allJava
    }
}


java {
    withSourcesJar()
    withJavadocJar()
}



description = "api"

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            group = "${project.group}.worldborderapi"
            authentication {
                credentials(PasswordCredentials::class)
            }
            val isSnap = version.toString().endsWith("SNAPSHOT");
            val releasesRepoUrl = "https://eldonexus.de/repository/maven-releases/"
            val snapshotsRepoUrl = "https://eldonexus.de/repository/maven-snapshots/"
            url = uri(if (isSnap) snapshotsRepoUrl else releasesRepoUrl)
            name = "eldonexus"
        }
    }
}
