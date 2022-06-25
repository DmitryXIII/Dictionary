plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 26
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(project(":domain"))
    implementation(project(":core"))

    implementation(Dependencies.CORE_KTX_DEP)
    implementation(Dependencies.APPCOMPAT_DEP)
    implementation(Dependencies.ANDROID_MATERIAL_DEP)
    implementation(Dependencies.COROUTINES_ANDROID_DEP)
    implementation(Dependencies.SPINKIT_DEP)
    implementation(Dependencies.LIFECYCLE_KTX_DEP)
    implementation(Dependencies.KOIN_DEP)
    testImplementation(Dependencies.JUNIT_DEP)
    androidTestImplementation(Dependencies.TEST_JUNIT_DEP)
}