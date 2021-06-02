plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.github.ainul.twisdev"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {

        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"https://ranting.twisdev.com/\"")
        }

        getByName("release") {
            buildConfigField("String", "BASE_URL", "\"https://ranting.twisdev.com/\"")

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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(Deps.AppConfig.kotlin)
    implementation(Deps.Androidx.androidCore)
    implementation(Deps.Androidx.appCompat)
    implementation(Deps.Androidx.constraintLayout)
    implementation(Deps.Androidx.legacySupport)
    testImplementation(Deps.Androidx.testJUnitImplementation)
    androidTestImplementation(Deps.Androidx.testJUnit)
    androidTestImplementation(Deps.Androidx.testEspresso)

    // UI material design
    implementation(Deps.Androidx.material)

    // http logging interceptor
    implementation(Deps.Network.httpInterceptor)

    // retrofit for http request
    implementation(Deps.Network.retrofit)

    // body parser for retrofit
    implementation(Deps.Network.gson)
    implementation(Deps.Network.gsonConverter)

    // coroutines
    implementation(Deps.Coroutines.coroutinesAndroid)
    implementation(Deps.Coroutines.coroutinesCore)

    // Dagger
    implementation(Deps.Dagger.dagger)
    kapt(Deps.Dagger.daggerCompiler)

    // lightweight image processing
    implementation(Deps.Bumptech.glide)
    kapt(Deps.Bumptech.glideCompiler)

    // jetpack fragment
    implementation(Deps.Jetpack.navigationFragment)
    implementation(Deps.Jetpack.navigationUI)
}