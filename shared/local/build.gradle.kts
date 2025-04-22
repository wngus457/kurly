plugins {
    id("android.model.convention")
}

android {
    namespace = "com.juhyeon.kurly.shared.local"
}

dependencies {
    ksp(libs.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)

    implementation(libs.datastore.preferences)
}
