plugins {
    alias(libs.plugins.example.core)
}


dependencies {
    implementation(projects.core.model)
    implementation(libs.androidx.compose.runtime)
}