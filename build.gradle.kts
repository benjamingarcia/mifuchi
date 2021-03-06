
plugins {
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
    id("com.github.johnrengelman.shadow") version "4.0.2"
    id("org.jetbrains.kotlin.jvm") version "1.2.61"
    id("org.jetbrains.kotlin.kapt") version "1.2.61"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.2.61"
    id("org.flywaydb.flyway") version "5.2.4"
    id("com.gradle.build-scan") version "2.2.1"
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"

    publishAlways()
}

apply(plugin = "application")

version = "0.1"
group = "mifuchi"
val kotlinVersion = "1.3.41"
val spekVersion = "2.0.5"
val junitVersion = "5.5.0"

repositories {
    jcenter()
}

dependencyManagement {
    imports {
        mavenBom("io.micronaut:micronaut-bom:1.1.4")
    }
}


dependencies {
    compile("io.micronaut", "micronaut-http-client")
    compile("io.micronaut", "micronaut-http-server-netty")
    compile("org.jetbrains.kotlin","kotlin-stdlib-jdk8", kotlinVersion)
    compile("org.jetbrains.kotlin","kotlin-reflect", kotlinVersion)
    compile("javax.annotation:javax.annotation-api")
    compile("io.micronaut","micronaut-runtime")
    compile("org.postgresql","postgresql","42.2.6")
    compile("io.micronaut.configuration","micronaut-postgres-reactive")
    kapt("io.micronaut:micronaut-inject-java")
    kapt("io.micronaut:micronaut-validation")
    kaptTest("io.micronaut:micronaut-inject-java")
    runtime("ch.qos.logback:logback-classic:1.2.3")
    runtime("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.7")
    testCompile("io.micronaut:micronaut-inject-java")
    testCompile("org.hamcrest:hamcrest-all:1.3")
    testCompile("org.junit.jupiter","junit-jupiter-api",junitVersion)
    testRuntime("org.junit.jupiter","junit-jupiter-engine",junitVersion)
    testImplementation("org.jetbrains.kotlin", "kotlin-test", kotlinVersion)
    testImplementation("org.spekframework.spek2", "spek-dsl-jvm", spekVersion)
    testRuntimeOnly("org.spekframework.spek2", "spek-runner-junit5", spekVersion)
}

tasks.shadowJar {
    mergeServiceFiles()
}


tasks.test {
    dependsOn(tasks.flywayMigrate)
    useJUnitPlatform{
        includeEngines("spek2")
    }
}

flyway{
    url = "jdbc:postgresql://localhost:5432/mifuchi"
    user = "mifuchi"
    password = "chifumi"
    schemas = Array(1){"mifuchi"}
}

configure<ApplicationPluginConvention> {
    mainClassName = "org.benji.mifuchi.Application"
}

tasks.named<JavaExec>("run") {
    jvmArgs("-noverify", "-XX:TieredStopAtLevel=1")
}

allOpen {
    annotation("io.micronaut.aop.Around")
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "1.8"
        //Will retain parameter names for Java reflection
        javaParameters = true
    }
}

tasks.compileTestKotlin {
    kotlinOptions {
        jvmTarget = "1.8"
        javaParameters = true
    }
}
