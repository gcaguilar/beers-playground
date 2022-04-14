plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.gcaguilar.untappd"
    compileSdk = 31

    defaultConfig {
        minSdk = 21

        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":feature:search:domain"))

    implementation(libs.bundles.dagger)
    implementation(libs.bundles.navigation)
    implementation(libs.viewmodel)
    implementation(libs.androidcoroutines)
    kapt(libs.hiltcompiler)
    kapt(libs.daggercompiler)
    implementation(libs.bundles.compose)
    implementation(libs.javaxinject)
    implementation(libs.navigationhilt)
    implementation(libs.coil)
}