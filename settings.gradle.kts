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
    }
}

rootProject.name = "Quote"
include(":app")
include(":core:l10n")
include(":core:domain")
include(":core:network")
include(":core:model")
include(":core:remote")
include(":core:local")
include(":core:designsystem")
include(":core:common")
include(":core:resource")
include(":features:quotes")
include(":features:favorite")
include(":features:profile")
include(":features:quotedetail")
//include(":dynamicfeature:df1")
