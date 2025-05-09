plugins {
    id ("com.android.application")
    kotlin ("android")
    id ("kotlin-parcelize")
    // id ("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
    id ("dagger.hilt.android.plugin")
    id ("com.google.devtools.ksp")
    id ("com.google.protobuf") version "0.9.4" apply false
    id ("org.jetbrains.kotlin.kapt")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    // id ("com.google.gms.google-services")
    // id ("com.google.firebase.crashlytics")
}

android {
    namespace = "com.verifyme.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.verifyme.app"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Compose Libs
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // API Client - Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // GSON
    implementation(libs.gson)

    // Jetpack Navigation
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    // Splash Screen
    implementation(libs.core.splashscreen)



}