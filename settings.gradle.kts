pluginManagement {
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
        maven("https://repository.map.naver.com/archive/maven")
        maven("https://naver.jfrog.io/artifactory/maven/")
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "price-it"

include(":app")
include(":core")
include(":feature")
include(":core:data")
include(":core:local")
include(":core:network")
include(":core:model")
include(":core:navigation")
include(":core:designsystem")
include(":feature:main")
include(":feature:auth")


includeBuild("build-logic") {
    name = "build-logic-included"
}

include(":feature:home")
include(":feature:my")
include(":feature:search")
include(":feature:request")
include(":feature:history")
