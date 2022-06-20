plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.safe.args)
}

android {
    namespace = "com.gcaguilar.untappd"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.gcaguilar.untappd"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-rc01"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.gcaguilar.untappd"
}

dependencies {
    implementation(project(":feature:search:di"))
    implementation(project(":feature:beer-detail:di"))
    implementation(project(":feature:authentication"))
    implementation(project(":navigation"))
    implementation(project(":common-ui"))

    implementation(libs.bundles.compose)
    implementation(libs.bundles.navigation)
    implementation(libs.navigationhilt)
    implementation(libs.bundles.dagger)
    implementation(libs.basecoroutines)
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.moshi)
    kapt(libs.moshicodegen)
    kapt(libs.hiltcompiler)
    kapt(libs.daggercompiler)
}