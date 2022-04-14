plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.gcaguilar.untappd.search.di"
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31

        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":feature:search:data"))
    implementation(project(":feature:search:domain"))
    implementation(project(":feature:search:presentation"))


    implementation(libs.bundles.dagger)
    kapt(libs.hiltcompiler)
    kapt(libs.daggercompiler)
    implementation(libs.viewmodel)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.moshi)
}