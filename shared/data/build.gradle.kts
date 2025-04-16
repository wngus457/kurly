plugins {
    id("android.kotlin.convention")
}

dependencies {
    implementation(projects.shared.util.kotlin)
    implementation(projects.shared.domain)
    implementation(projects.shared.navigation)
    implementation(libs.java.inject)
}