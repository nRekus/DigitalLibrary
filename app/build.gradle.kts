import org.gradle.kotlin.dsl.implementation
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    //id("org.jetbrains.kotlin.compose")
    //alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.visitenkarte"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.visitenkarte"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
}
kotlin{
    compilerOptions{
        jvmTarget.set(JvmTarget.JVM_17)
    }
}
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Compose Viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // retrofit
    implementation(libs.retrofit.core)
    // gson converter
    implementation(libs.retrofit.converter.gson)

    // ROOM (Database) Dependencies
    implementation(libs.androidx.room3.runtime)
    ksp(libs.androidx.room3.compiler)

    // Navigation Dependencies
    // Jetpack Compose integration
//    implementation(libs.androidx.navigation.compose)
//    // Views/Fragments integration
//    implementation(libs.androidx.navigation.fragment)
//    implementation(libs.androidx.navigation.ui)
//    // Feature module support for Fragments
//    implementation(libs.androidx.navigation.dynamic.features.fragment)
//    // Testing Navigation
//    androidTestImplementation(libs.androidx.navigation.testing)
//    // JSON serialization library, works with the Kotlin serialization plugin
//    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}