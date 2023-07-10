val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val postgresVersion: String by project
val exposedVersion: String by project
val h2Version: String by project
val prometeusVersion: String by project
val gsonVersion: String by project
val hikaricpVersion: String by project
val redisVersion: String by project
val jbcryptVersion: String by project
val databaseDriverVersion: String by project

plugins {
	kotlin("jvm") version "1.9.0"
	id("io.ktor.plugin") version "2.3.2"
	id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
}

group = "com.polygon"
version = "0.0.1"
application {
	mainClass.set("com.polygon.ApplicationKt")
	
	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("ch.qos.logback:logback-classic:$logbackVersion")
	implementation("com.google.code.gson:gson:$gsonVersion")
	implementation("org.mindrot:jbcrypt:$jbcryptVersion")
	implementation("io.github.crackthecodeabhi:kreds:$redisVersion")
	implementation("com.zaxxer:HikariCP:$hikaricpVersion")
	implementation("com.h2database:h2:$h2Version")
	implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
	implementation("org.postgresql:postgresql:$postgresVersion")
	implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-websockets-jvm:$ktorVersion")
	implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-metrics-micrometer-jvm:$ktorVersion")
	implementation("io.micrometer:micrometer-registry-prometheus:$prometeusVersion")
	implementation("io.ktor:ktor-server-metrics-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-call-logging-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-call-id-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-partial-content-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-swagger-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-openapi:$ktorVersion")
	implementation("io.ktor:ktor-server-forwarded-header-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-default-headers-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-cors-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-conditional-headers-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-compression-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-caching-headers-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-resources:$ktorVersion")
	implementation("io.ktor:ktor-server-auth-jvm:$ktorVersion")
	implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
	implementation("io.ktor:ktor-client-apache-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
	implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
	implementation("io.ktor:ktor-server-auth:$ktorVersion")
	implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
	implementation("io.ktor:ktor-server-auth-jwt:$ktorVersion")
	testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
	testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
	testImplementation("io.ktor:ktor-server-test-host-jvm:$ktorVersion")
}