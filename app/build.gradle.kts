plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.android.hilt)
    id("androidx.navigation.safeargs.kotlin")

}

android {
    namespace = "com.example.e_learning"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.e_learning"
        minSdk = 26
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
        viewBinding = true
        dataBinding = true
    }

}

dependencies {

    implementation(libs.androidx.multidex)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    //navigation component
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    //shimmer effect
    implementation(libs.shimmer.effect)
    //rounded image
    implementation(libs.roundedimage)

    //glide
    implementation(libs.glide)
    //picasso
    implementation(libs.picasso)




    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.hilt.android.v25)
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(libs.androidx.material3.android)
    kapt(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation(libs.androidx.datastore.preferences)

    implementation ("androidx.annotation:annotation:1.4.0")
    implementation ("androidx.appcompat:appcompat:1.4.2")
    implementation ("com.github.shuhart:stepview:1.5.1")

    implementation (libs.lottie)

    implementation (libs.material)






}