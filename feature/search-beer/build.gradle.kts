import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.android.safe.args)
}

android {
    namespace = "com.gcaguilar.untappd.search.presentation"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        kotlinCompilerExtensionVersion = "1.5.6"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions.freeCompilerArgs = listOf(
            *kotlinOptions.freeCompilerArgs.toTypedArray(),
            "-opt-in=androidx.lifecycle.compose.ExperimentalLifecycleComposeApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
            "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi"
        )
    }
}

dependencies {
    implementation(project(mapOf("path" to ":common-ui")))
    implementation(project(mapOf("path" to ":core-data")))

    implementation(libs.javaxinject)
    implementation(libs.basecoroutines)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.moshi)
    ksp(libs.moshicodegen)
    implementation(libs.bundles.dagger)
    implementation(libs.bundles.navigation)
    implementation(libs.viewmodel)
    implementation(libs.androidcoroutines)
    ksp(libs.hiltcompiler)
    ksp(libs.daggercompiler)
    implementation(libs.bundles.compose)
    implementation(libs.javaxinject)
    implementation(libs.navigationhilt)
    implementation(libs.coil)
    implementation(libs.accompanist.placeholder)
}