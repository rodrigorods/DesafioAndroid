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
}

rootProject.name = "DesafioAndroidPicPay"
include(":app")


include(":user-management-domain")
project(":user-management-domain").projectDir = File(rootDir, "domain/user-management")

include(":user-management-data")
project(":user-management-data").projectDir = File(rootDir, "data/user-management")

include(":user-management-ui")
project(":user-management-ui").projectDir = File(rootDir, "presentation/user-management")

include(":user-management-injector")
project(":user-management-injector").projectDir = File(rootDir, "injector/user-management")
