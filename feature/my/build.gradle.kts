plugins {
    alias(libs.plugins.example.feature)
}

dependencies {
    implementation(libs.coil.compose)
    implementation(projects.feature.history)
    implementation(libs.androidx.compose.foundation.layout)
}