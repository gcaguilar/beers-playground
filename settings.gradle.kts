pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

include(":app")
include(":navigation")
include(":common-ui")
include(":feature:beer-detail:di")
include(":feature:beer-detail:data")
include(":feature:beer-detail:presentation")
include(":feature:beer-detail:domain")
include(":feature:authentication")
include(":feature:search-beer")
include(":core-data")
