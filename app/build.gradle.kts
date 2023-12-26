plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.ksp)
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }

    namespace = "com.gcaguilar.untappd"
}

dependencies {
    implementation(project(":feature:search-beer"))
    implementation(project(":feature:authentication"))
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
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.test.manifest)
    ksp(libs.moshicodegen)
    ksp(libs.hiltcompiler)
    ksp(libs.daggercompiler)
}