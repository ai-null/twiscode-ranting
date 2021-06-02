object Deps {

    object AppConfig {
        private const val kotlinVersion = "1.4.32"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

    }

    object Androidx {
        const val androidCore = "androidx.core:core-ktx:1.3.2"
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val material = "com.google.android.material:material:1.3.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val legacySupport = "androidx.legacy:legacy-support-v4:1.0.0"
        const val testJUnit = "androidx.test.ext:junit:1.1.2"
        const val testJUnitImplementation = "junit:junit:4.+"
        const val testEspresso = "androidx.test.espresso:espresso-core:3.3.0"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val gson = "com.google.code.gson:gson:2.8.6"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:2.4.0"
        const val httpInterceptor = "com.squareup.okhttp3:logging-interceptor:4.2.2"
    }

    object Coroutines {
        private const val coroutinesVersion = "1.4.2"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    }

    object Dagger {
        private const val daggerVersion = "2.27"
        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    }

    object Bumptech {
        const val glide = "com.github.bumptech.glide:glide:4.12.0"
        const val glideCompiler = "com.github.bumptech.glide:compiler:4.12.0"
    }

    object Jetpack {
        private const val navVersion = "2.3.5"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navVersion"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:$navVersion"
    }
}