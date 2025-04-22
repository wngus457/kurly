plugins {
    id("android.library.convention")
}

android {
    namespace = "com.juhyeon.kurly.shared.ui.common"
}

dependencies {
    implementation(libs.compose.navigation)

    implementation(libs.coil.gif)
}