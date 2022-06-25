plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.ineedyourcode.dictionary"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.RETROFIT_DEP)
    implementation(Dependencies.RETROFIT_GSON_CONVERTER_DEP)
    implementation(Dependencies.LOGGING_INTERCEPTOR_DEP)
    implementation(Dependencies.RETROFIT_COROUTINES_ADAPTER_DEP)

    implementation(Dependencies.KOIN_DEP)

    implementation(Dependencies.SPINKIT_DEP)

    implementation(Dependencies.LOTTIE_DEP)

    implementation(Dependencies.COROUTINES_ANDROID_DEP)

    implementation(Dependencies.ROOM_RUNTIME_DEP)
    implementation(Dependencies.ROOM_KTX_DEP)
    kapt(Dependencies.ROOM_COMPILER_DEP)

    implementation(Dependencies.COIL_DEP)

    implementation(Dependencies.LIFECYCLE_KTX_DEP)

    implementation(Dependencies.FRAGMENT_KTX_DEP)
    implementation(Dependencies.CORE_KTX_DEP)
    implementation(Dependencies.APPCOMPAT_DEP)
    implementation(Dependencies.ANDROID_MATERIAL_DEP)
    implementation(Dependencies.CONSTRAINT_LAYOUT_DEP)
    testImplementation(Dependencies.JUNIT_DEP)
    androidTestImplementation(Dependencies.TEST_JUNIT_DEP)
    androidTestImplementation(Dependencies.TEST_ESPRESSO_DEP)
}