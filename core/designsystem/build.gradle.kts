plugins {
    alias(libs.plugins.example.core)
    alias(libs.plugins.kotlin.compose)
}
android {
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.androidx.ui.graphics.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.runtime.android)
}