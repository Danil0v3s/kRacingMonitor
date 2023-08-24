import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    id("org.jetbrains.compose") version "1.1.1"
    id("com.diffplug.spotless") version "6.1.0"
}

group = "br.com.firstsoft"
version = "0.0.3"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation("net.java.dev.jna:jna-platform:5.9.0")

    implementation("io.ktor:ktor-server-netty:1.6.7")
    implementation("io.ktor:ktor-websockets:1.6.7")
    implementation("io.ktor:ktor-serialization:1.6.7")
    implementation("io.ktor:ktor-server-core:1.6.7")
    implementation("io.ktor:ktor-auth:1.6.7")

    implementation("ch.qos.logback:logback-classic:1.2.10")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.compose.material:material-icons-extended-desktop:1.1.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

sourceSets {
    main {
        java {
            srcDir("src/main/kotlin")
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Msi)
            packageName = "kMonitor"
            packageVersion = "0.0.3"
        }
    }
}

spotless {
    format("misc") {
        target("*.gradle", "*.md", ".gitignore")

        trimTrailingWhitespace()
        indentWithTabs()
        endWithNewline()
    }
    kotlin {
        ktlint()
    }
}