plugins {
    id("android.domain.convention")
    alias(libs.plugins.serialization)
}

dependencies {
    implementation(libs.java.inject)
    implementation(libs.serialization)
}