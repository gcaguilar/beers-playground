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

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_11)
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.gcaguilar.untappd"
}

dependencies {
    implementation(project(":feature:search-beer"))
    implementation(project(":feature:beer-detail:di"))
    implementation(project(":feature:beer-detail:presentation"))
    implementation(project(":feature:authentication"))
    implementation(project(":navigation"))
    implementation(project(":common-ui"))

    implementation(libs.bundles.compose)
    implementation(libs.bundles.navigation)
    implementation(libs.navigationhilt)
    implementation(libs.bundles.dagger)
    implementation(libs.basecoroutines)
    implementation(libs.splashscreen)
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.moshi)
    kapt(libs.moshicodegen)
    kapt(libs.hiltcompiler)
    kapt(libs.daggercompiler)
}