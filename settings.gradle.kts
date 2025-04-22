pluginManagement {
    repositories {
        google()
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
    versionCatalogs {
        create("libs") {
            library("androidx.monitor", "androidx.test:monitor:1.6.1")
            library("androidx.junit.ktx", "androidx.test.ext:junit-ktx:1.1.5")
        }
    }
}

rootProject.name = "AnimeViewer"
include(":app")