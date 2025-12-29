import java.util.Properties

plugins {
    alias(libs.plugins.example.android.application)
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

android {
    defaultConfig {
        applicationId = "project.priceit"
        resValue("string", "naver_client_id", localProperties.getProperty("naver_client_id"))
    }
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.feature.main)

    // Naver Maps
    implementation(libs.naver.map.compose)
    implementation(libs.naver.map.location)
}