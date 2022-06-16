plugins {
    kotlin("jvm") version "1.5.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.github.vldf:libsl:4b2a5d4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
    implementation("com.squareup.okhttp3:okhttp:3.9.0")
    implementation("javax.mail:javax.mail-api:1.6.2")
    // https://mvnrepository.com/artifact/org.jeasy/easy-random
    implementation("org.jeasy:easy-random-core:5.0.0")
    implementation("info.picocli:picocli:4.6.3")


    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")




}

tasks.test {
    useJUnitPlatform()
}
