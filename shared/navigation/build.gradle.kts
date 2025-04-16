plugins {
    id("android.kotlin.convention")
    alias(libs.plugins.serialization)
}

dependencies {
    implementation(libs.coroutine.core.jvm)
    implementation(libs.serialization)
}