// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    val kotlinVersion = "2.1.0"
    repositories {
        google()
        mavenCentral()
    }
    dependencies{
        classpath("com.android.tools.build:gradle:8.8.0")
        classpath("com.android.tools.external.org-jetbrains:uast:31.9.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}