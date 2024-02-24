plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.navigationSafeArgs)
    alias(libs.plugins.kotlinKsp)
    alias(libs.plugins.daggerHilt)
}

android {
    namespace = "com.softcross.onepiece"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.softcross.onepiece"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures{
        viewBinding = true
    }

}

dependencies {
    //Android core
    implementation(libs.androidx.core.ktx)
    //Android appCompat
    implementation(libs.androidx.appcompat)
    //Material
    implementation(libs.material)
    //Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.okhttp3.logging.interceptor)
    //Android legacy support
    implementation(libs.androidx.legacy.support)
    //LiveData - Lifecycle
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewModel)
    //Hilt
    implementation(libs.hilt.adroid)
    ksp(libs.hilt.compiler)
    //Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}