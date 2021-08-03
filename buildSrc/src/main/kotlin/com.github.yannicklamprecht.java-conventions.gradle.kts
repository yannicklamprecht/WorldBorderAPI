plugins {
    `java-library`
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    maven {
        url = uri("https://repo.aikar.co/content/groups/aikar/")
    }
}

group = "com.github.yannicklamprecht"
version = "1.171.0"



tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

plugins.withId("java") {
    the<JavaPluginExtension>().toolchain {
        languageVersion.set(JavaLanguageVersion.of(16))
        vendor.set(JvmVendorSpec.ADOPTOPENJDK)
    }
}
