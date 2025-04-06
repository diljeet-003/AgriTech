plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}
android {
    namespace = "com.example.agritech"
    compileSdk = 35
    viewBinding {
        enable=true
    }
    dataBinding {
        enable=true
    }

    defaultConfig {
        applicationId = "com.example.agritech"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("androidx.core:core-ktx:1.7.0")

    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.5.0")
    implementation ("com.google.android.material:material:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.1")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.1")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    implementation ("com.github.CanHub:Android-Image-Cropper:4.2.1")


    // Retrofit & OkHttp
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // JSON Converter
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("com.github.bumptech.glide:glide:4.13.2")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.13.2")

    implementation ("androidx.room:room-runtime:2.4.3")


    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // circle image
    implementation ("de.hdodenhof:circleimageview:3.1.0")


}