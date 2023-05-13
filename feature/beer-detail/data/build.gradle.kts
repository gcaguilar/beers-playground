plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":feature:beer-detail:domain"))

    implementation(libs.javaxinject)
    implementation(libs.basecoroutines)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.moshi)
    ksp(libs.moshicodegen)
}
