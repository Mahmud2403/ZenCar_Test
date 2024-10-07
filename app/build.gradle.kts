plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.zencar_test"
    compileSdk = 34

//    kapt {
//        arguments{
//            arg("room.schemaLocation", "$projectDir/schemas".toString())
//        }
//    }

    defaultConfig {
        applicationId = "com.example.zencar_test"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)

    //Compose
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(libs.material3)
    implementation(libs.material)
    implementation(libs.animation)
    implementation(libs.ui)
    implementation(libs.activity)
    implementation(libs.graphics)
    implementation(libs.tooling.preview)
    implementation(libs.material.icons.extended)
    implementation(libs.util)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.tooling)
    debugImplementation(libs.test.manifest)

    testImplementation(libs.junit)
    androidTestImplementation(libs.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)

    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //Hilt
    implementation(libs.hilt.navigation)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
//    implementation(libs.startup)

    //Room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    //System UI Controller
    implementation(libs.google.accompanist.systemUiController)

    //ImageLoader
    implementation(libs.coil.compose)

}