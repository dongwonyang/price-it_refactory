import java.util.Properties

plugins {
    alias(libs.plugins.example.feature)
}

dependencies {
    // Naver Maps
    implementation(libs.naver.map.compose)
    implementation(libs.naver.map.location)

    // Permissions
    implementation(libs.accompanist.permissions)
}