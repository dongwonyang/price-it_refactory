plugins {
    alias(libs.plugins.example.feature)
}

dependencies {
    implementation(projects.feature.auth)
    implementation(projects.feature.home)
    implementation(projects.feature.search)
    implementation(projects.feature.request)
    implementation(projects.feature.my)
    implementation(projects.feature.history)

    implementation(libs.kotlinx.immutable)
}