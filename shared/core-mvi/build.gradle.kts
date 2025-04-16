plugins {
    id("android.kotlin.convention")
}

dependencies {
    implementation(libs.coroutine.core.jvm)
    implementation(libs.lifecycle.viewmodel)
}