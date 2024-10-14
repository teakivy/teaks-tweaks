plugins {
    `java-library`
    id("com.gradleup.shadow") version "8.3.3"
}

group = "me.teakivy"
version = "2.0.1"
description = "150+ Toggleable Tweaks & Features including Vanilla Tweaks as a plugin, and more!"
java.sourceCompatibility = JavaVersion.VERSION_21

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
}

val libraries = listOf(
    "net.kyori:adventure-text-minimessage:4.17.0",
    "net.kyori:adventure-platform-bukkit:4.3.3",
    "com.googlecode.json-simple:json-simple:1.1.1",
    "com.squareup.okhttp3:okhttp:4.9.3"
)

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21-R0.1-SNAPSHOT")
    compileOnly("com.mojang:authlib:1.5.25")

    libraries.forEach { library ->
        compileOnly(library)
    }
}

tasks {
    shadowJar {
        archiveFileName.set("TeaksTweaks-${project.version}.jar")
//        destinationDirectory.set(file("C:/Users/legoc/Desktop/Servers/TeaksTweaks Testing/plugins"))
        minimize()
    }
    jar {
        // change output directory
        archiveFileName.set("TeaksTweaks-${project.version}.jar")
        destinationDirectory.set(file("C:/Users/legoc/Desktop/Servers/TeaksTweaks Testing/plugins"))
    }
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand(
            "version" to project.version,
            "libraries" to libraries.joinToString("\n  - ")
        )
    }
}
