package project.priceit.convention.plugin

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import project.priceit.convention.configureKotlinAndroid
import project.priceit.convention.util.libs
import project.priceit.convention.util.setNamespace

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")

            setNamespace(path.replace(":", ".").removePrefix("."))

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }

            val libs = extensions.libs

            dependencies {
                add("implementation", libs.findLibrary("timber").get())
                add("implementation", libs.findLibrary("kotlinx-coroutines-core").get())
                add("implementation", libs.findLibrary("kotlinx-coroutines-android").get())
            }
        }
    }
}
