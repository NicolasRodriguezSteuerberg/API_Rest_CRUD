plugins {
    id("java")
}

group = "org.nsteuerberg"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // jackson
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")
    implementation("org.springframework:spring-web:6.1.10")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}