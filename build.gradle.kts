
plugins {
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
    id("com.github.johnrengelman.shadow") version "4.0.2"
    id("org.jetbrains.kotlin.jvm") version "1.2.61"
    id("org.jetbrains.kotlin.kapt") version "1.2.61"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.2.61"
}

apply(plugin = "application")

version = "0.1"
group = "mifuchi"
val kotlinVersion = "1.3.21"
val spekVersion = "1.1.5"
val junitVersion = "5.1.0"

repositories {
    mavenLocal()
    mavenCentral()
    maven(url = "https://jcenter.bintray.com")
}

dependencyManagement {
    imports {
        mavenBom("io.micronaut:micronaut-bom:1.1.0.M1")
    }
}


dependencies {
    compile("io.micronaut", "micronaut-http-client")
    compile("io.micronaut", "micronaut-http-server-netty")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    compile("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    compile("io.micronaut:micronaut-runtime")
    compile("javax.annotation:javax.annotation-api")
    kapt("io.micronaut:micronaut-inject-java")
    kapt("io.micronaut:micronaut-validation")
    kaptTest("io.micronaut:micronaut-inject-java")
    runtime("ch.qos.logback:logback-classic:1.2.3")
    runtime("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.7")
    testCompile("junit:junit:4.12")
    testCompile("io.micronaut:micronaut-inject-java")
    testCompile("org.hamcrest:hamcrest-all:1.3")
    testCompile("org.junit.jupiter:junit-jupiter-api:5.1.0")
    testCompile("org.jetbrains.spek:spek-api:1.1.5")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.1.0")
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.5")
}

tasks.shadowJar {
    mergeServiceFiles()
}


tasks.test {
    useJUnitPlatform()
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
