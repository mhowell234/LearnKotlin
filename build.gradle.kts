plugins {
    kotlin("jvm") version "1.8.21"
}

group = "com.mhowell234"
version = "1.0-SNAPSHOT"
description = "learn-kotlin"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("dom4j:dom4j:1.1")
    compileOnly("org.projectlombok:lombok:1.18.24")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation(kotlin("test"))
}

tasks.jar {
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(19)
}
