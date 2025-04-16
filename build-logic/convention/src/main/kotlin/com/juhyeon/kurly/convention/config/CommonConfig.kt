package com.juhyeon.kurly.convention.config

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.JavaPluginExtension
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

internal fun Project.configureCommonExtension(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        compileSdk = 35

        defaultConfig {
            minSdk = 26
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        kotlin {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }
    }
}

fun Project.java(block: JavaPluginExtension.() -> Unit) {
    (this as ExtensionAware).extensions.configure("java", block)
}

fun Project.kotlin(block: KotlinAndroidProjectExtension.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlin", block)
}

fun Project.composeCompiler(block: ComposeCompilerGradlePluginExtension.() -> Unit) {
    (this as ExtensionAware).extensions.configure("composeCompiler", block)
}

fun KotlinAndroidProjectExtension.sourceSets(block: NamedDomainObjectContainer<KotlinSourceSet>.() -> Unit) {
    block(sourceSets)
}

fun NamedDomainObjectContainer<KotlinSourceSet>.debug(block: KotlinSourceSet.() -> Unit) {
    getByName("debug", block)
}

fun NamedDomainObjectContainer<KotlinSourceSet>.release(block: KotlinSourceSet.() -> Unit) {
    getByName("release", block)
}