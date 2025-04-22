buildscript {
    dependencies {
        classpath(libs.hilt.plugin)
        classpath(libs.agp)
        classpath(libs.ksp)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.serialization)
    }
}

allprojects {
    val ktlint by configurations.creating
    dependencies {
        ktlint("com.pinterest:ktlint:0.46.1") {
            attributes {
                attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
            }
        }
    }

    val outputDir = "${project.buildDir}/reports/ktlint/"
    val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "main/**/*.kt"))

    val ktlintCheck by tasks.creating(JavaExec::class) {
        inputs.files(inputFiles)
        outputs.dir(outputDir)
        description = "Local Check Code Style"
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args = listOf(
            "src/main/**/*.kt",
            "**/**/src/main/**/*.kt",
            "**/*.kts",
            "*.kts",
            "**/**/src/**/*.kt",
            "build-logic/**/*.kt"
        )
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.serialization) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("android.app.convention") apply false
    id("android.kotlin.convention") apply false
    id("android.model.convention") apply false
    id("android.domain.convention") apply false
    id("android.library.convention") apply false
    id("android.feature.convention") apply false
}
