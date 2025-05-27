plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.devtools.ksp")
    id ("dagger.hilt.android.plugin")

}

android {
    namespace = "com.example.planty"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.planty"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))
    implementation("com.google.firebase:firebase-analytics" )
    implementation("com.google.firebase:firebase-crashlytics")
    implementation(libs.firebase.messaging)
    //Room
    implementation ("androidx.room:room-runtime:2.7.1")
    implementation(libs.androidx.espresso.core)
    ksp ("androidx.room:room-compiler:2.7.1")
    implementation ("androidx.room:room-ktx:2.7.1")

    //Compose
    implementation("androidx.navigation:navigation-compose:2.9.0")

    //Splash
    implementation("androidx.core:core-splashscreen:1.0.1")

    //Hilt
    implementation (libs.hilt.android)
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    ksp (libs.dagger.hilt.compiler)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}