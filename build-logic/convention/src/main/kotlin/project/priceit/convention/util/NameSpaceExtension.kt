package project.priceit.convention.util

import org.gradle.api.Project

fun Project.setNamespace(name: String) {
    androidExtension.apply {
        namespace = "project.priceit.$name"
    }
}