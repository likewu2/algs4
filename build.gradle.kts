plugins {
    val kotlinVersion = "2.0.20"

    application

    kotlin("jvm") version kotlinVersion
}

group = "com.pvtool"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenLocal()
  //maven(url = "https://repo.spring.io/release")
  maven(url="https://maven.aliyun.com/repository/public")  //jcenter
  maven(url="https://maven.aliyun.com/repository/central")
  maven(url = "https://maven.aliyun.com/repository/spring")
  maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
  mavenCentral()
}

dependencies {
}

application {
    mainClass.set("com.pvtool.Main2")
}
