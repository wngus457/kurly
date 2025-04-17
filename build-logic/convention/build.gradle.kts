plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.agp)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp)
    compileOnly(libs.compose.compiler.extension)
}

gradlePlugin {
    plugins {
        register("AndroidApplicationPlugin") {
            id = "android.app.convention"
            implementationClass = "com.juhyeon.kurly.convention.AndroidApplicationConventionPlugin"
        }
        register("KotlinPlugin") {
            id = "android.kotlin.convention"
            implementationClass = "com.juhyeon.kurly.convention.KotlinConventionPlugin"
        }
        register("ModelPlugin") {
            id = "android.model.convention"
            implementationClass = "com.juhyeon.kurly.convention.ModelConventionPlugin"
        }
        register("DomainPlugin") {
            id = "android.domain.convention"
            implementationClass = "com.juhyeon.kurly.convention.DomainConventionPlugin"
        }
        register("LibraryPlugin") {
            id = "android.library.convention"
            implementationClass = "com.juhyeon.kurly.convention.SharedLibraryConventionPlugin"
        }
        register("FeaturePlugin") {
            id = "android.feature.convention"
            implementationClass = "com.juhyeon.kurly.convention.FeatureConventionPlugin"
        }
    }
}