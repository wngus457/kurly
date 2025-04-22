plugins {
    id("android.domain.convention")
    alias(libs.plugins.serialization)
}

dependencies {
    implementation(projects.shared.domain)

    implementation(libs.java.inject)
    implementation(libs.serialization)
}