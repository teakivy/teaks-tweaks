plugins {
    `java-library`
}

group = "me.teakivy"
version = "2.0.7-mc1.21.4"
description = "150+ Toggleable Tweaks & Features including Vanilla Tweaks as a plugin, and more!"
java.sourceCompatibility = JavaVersion.VERSION_21

val outputDir: String? by project

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    maven {
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }

    maven {
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }

    maven {
        url = uri("https://libraries.minecraft.net/")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }

    maven {
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
}

val libraries = listOf(
    "net.kyori:adventure-text-minimessage:4.17.0",
    "net.kyori:adventure-platform-bukkit:4.3.3",
    "com.googlecode.json-simple:json-simple:1.1.1",
    "com.squareup.okhttp3:okhttp:4.9.3"
)

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21.5-R0.1-SNAPSHOT")
    compileOnly("com.mojang:authlib:1.5.25")
    compileOnly("me.clip:placeholderapi:2.11.6")

    libraries.forEach { library ->
        compileOnly(library)
    }
}

tasks.jar {
    val customDir = outputDir ?: layout.buildDirectory.dir("libs")

    archiveFileName.set("TeaksTweaks-v${project.version}.jar")
    destinationDirectory.set(file(customDir))
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand(
            "version" to project.version,
            "libraries" to libraries.joinToString("\n  - ")
        )
    }
}
