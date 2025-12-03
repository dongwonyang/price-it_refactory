plugins {
    alias(libs.plugins.example.android.application)
}

android {
    defaultConfig {
        applicationId = "project.priceit"
    }
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.feature.main)
}