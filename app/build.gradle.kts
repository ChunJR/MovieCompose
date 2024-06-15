plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.dagger.hilt.android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
}

android {
    namespace = "com.chun.moviecompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.chun.moviecompose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.compose.material:material:1.5.2")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.7.20")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.7.20")
    testImplementation("org.mockito:mockito-core:3.+")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.0")

    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.0-beta01")

    // System UI Controller - Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    // serialization
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.7.20")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("io.coil-kt:coil-video:2.2.2")

    // Paging 3.0
    implementation("androidx.paging:paging-compose:3.2.0-rc01")
}

// Hilt
// Allow references to generated code
kapt {
    correctErrorTypes = true
}