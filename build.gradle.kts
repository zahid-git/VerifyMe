// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version "8.5.0" apply false
    //  id ("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
    id ("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id ("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false
    alias(libs.plugins.kotlin.compose) apply false
    // id ("com.google.gms.google-services") version "4.4.2" apply false
    // id ("com.google.firebase.crashlytics") version "3.0.2" apply false
}


buildscript {
    dependencies {
        classpath(libs.build.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}

tasks.withType<org.jetbrains.kotlin.gradle.internal.KaptWithoutKotlincTask>()
    .configureEach { kaptProcessJvmArgs.add("-Xmx512m") }
