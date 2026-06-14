plugins {
  kotlin("jvm") version "2.0.21"
  id("java")
  id("maven-publish")
}

group = "harmony.library"
version = "1.2.0-modern"

repositories {
  mavenCentral()
  maven("https://repo.papermc.io/repository/maven-public/")
  maven("https://jitpack.io")
}

dependencies {
  implementation(kotlin("reflect"))

  compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
  compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
}

kotlin {
  jvmToolchain(21)
}

publishing {
  publications {
    create<MavenPublication>("maven") {
      from(components["kotlin"])
    }
  }
}