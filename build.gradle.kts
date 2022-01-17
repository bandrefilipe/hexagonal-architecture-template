import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("org.springframework.boot") version "2.6.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.spring") version "1.6.10"
}

dependencies {
    implementation(project(":application"))
    implementation(project(":domain"))
    implementation(project(":persistence"))
    implementation(project(":rest-api"))

    // Spring Boot Starter.
    implementation("org.springframework.boot:spring-boot-starter")
}

allprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    version = "0.0.1"

    repositories {
        mavenCentral()
    }

    dependencies {
        when (project.name) {
            "commons" -> Unit
            else -> implementation(project(":commons"))
        }

        // Kotlin Full Reflection Library.
        implementation(kotlin("reflect"))

        // Kotlin Standard Library JDK 8 extension.
        implementation(kotlin("stdlib-jdk8"))

        // Lightweight logging framework for Kotlin.
        implementation("io.github.microutils:kotlin-logging:2.1.21")

        // Kotlin Test Support for junit5.
        testImplementation(kotlin("test-junit5"))
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.majorVersion
            freeCompilerArgs = listOf(
                "-Xjsr305=strict",
                "-Xemit-jvm-type-annotations"
            )
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

subprojects {
    apply {
        plugin("java-library")
    }

    dependencyManagement {
        imports {
            mavenBom(SpringBootPlugin.BOM_COORDINATES)
        }
    }

    tasks.withType<Jar> {
        archiveBaseName.set(rootProject.name)
        archiveAppendix.set(project.name)
    }
}
