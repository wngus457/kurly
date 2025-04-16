plugins {
    id("android.model.convention")
}

android {
    namespace = "com.juhyeon.kurly.shared.remote"
}

dependencies {
    implementation(projects.shared.util.android)
    implementation(projects.shared.util.kotlin)

    implementation(libs.bundles.retrofit)
}