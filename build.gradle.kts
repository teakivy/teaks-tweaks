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

dependencies {
    compileOnly("org.spigotmc:spigot:1.21.1-R0.1-SNAPSHOT:remapped-mojang")
    compileOnly("com.mojang:authlib:1.5.25")

    // If you ever decide to add more libraries or change these, make sure to update plugin.yml to match.
    compileOnly("net.kyori:adventure-text-minimessage:4.17.0")
    compileOnly("net.kyori:adventure-platform-bukkit:4.3.3")
    compileOnly("com.googlecode.json-simple:json-simple:1.1.1")
    compileOnly("com.squareup.okhttp3:okhttp:4.9.3")
}

tasks {
    shadowJar {
        archiveFileName.set("TeaksTweaks-${project.version}.jar")
        minimize()
    }
    jar {
//        destinationDirectory.set(File("C:/Users/legoc/Desktop/Servers/TeaksTweaks Testing/plugins"))
    }
}
