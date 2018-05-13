buildscript {
    repositories {
        maven {
            setUrl("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.31")
    }
}
allprojects {
    group = "com.itcast.gradle"
    version = "1.0-SNAPSHOT"
    apply {
        plugin("org.jetbrains.kotlin.jvm")
    }
    repositories {
        mavenCentral()
    }
}

