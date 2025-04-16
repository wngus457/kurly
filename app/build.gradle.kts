plugins {
    id("android.app.convention")
}

android {
    namespace = "com.juhyeon.kurly"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/versions/9/previous-compilation-data.bin"
        }
    }
}