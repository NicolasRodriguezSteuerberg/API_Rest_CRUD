plugins {
    id("java")
}

group = "com.nsteuerberg"
version = "0.0.1si "

repositories {
    mavenCentral()
}

dependencies {
    // jackson
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")
    // spring
    implementation("org.springframework:spring-context:6.1.10")
    implementation("org.springframework:spring-core:6.1.10")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}