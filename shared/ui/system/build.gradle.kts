plugins {
    id("android.library.convention")
    alias(libs.plugins.serialization)
}

android {
    namespace = "com.juhyeon.kurly.shared.ui.system"
}

dependencies {
    implementation(projects.shared.coreMvi)
    implementation(projects.shared.util.android)
    implementation(projects.shared.navigation)
    implementation(projects.shared.ui.common)
    implementation(projects.shared.ui.presenters)

    implementation(libs.compose.navigation)

    implementation(libs.coil.gif)

    implementation(libs.accompainst.permission)

    implementation(libs.lottie.compose)

    implementation(libs.serialization)
}