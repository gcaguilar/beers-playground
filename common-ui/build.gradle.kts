plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}


android {
    namespace = "com.gcaguilar.common_ui"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions.freeCompilerArgs = listOf(
            *kotlinOptions.freeCompilerArgs.toTypedArray(),
            "-opt-in=androidx.lifecycle.compose.ExperimentalLifecycleComposeApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi"
        )
    }
}

dependencies {
    implementation(libs.composefoundation)
    implementation(libs.composematerial)
    implementation(libs.composeicon)
}