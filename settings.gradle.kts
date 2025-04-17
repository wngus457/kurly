pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Kurly"
include(":app")
include(":shared:core-mvi")
include(":shared:remote")
include(":shared:domain")
include(":shared:data")
include(":shared:util:kotlin")
include(":shared:util:android")
include(":shared:ui:system")
include(":shared:ui:common")
include(":shared:navigation")
include(":shared:local")
include(":mockserver")
