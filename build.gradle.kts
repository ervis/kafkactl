plugins {
  application
  kotlin("jvm") version "1.5.10"
}

group = "com.erviszyka.kafkactl"
version = "0.1-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.2")
  implementation("org.apache.kafka:kafka-clients:2.8.0")

//  implementation("org.slf4j:slf4j-api:1.7.31")
//  implementation("org.slf4j:slf4j-simple:1.7.31")
  implementation("ch.qos.logback:logback-classic:1.2.3")

  testImplementation(platform("org.junit:junit-bom:5.7.2"))
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions.jvmTarget = "16"
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
  }
}
application {
  // Define the main class for the application
  mainClass.set("com.erviszyka.kafkactl.MainKt")
}

val jar by tasks.getting(Jar::class) {
  manifest {
    attributes["Main-Class"] = "com.erviszyka.kafkactl.MainKt"
  }
  from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
  duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
