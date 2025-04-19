plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kotlinCompose)
}

android {
    namespace = "net.harutiro.nationalweather"
    compileSdk = 35

    defaultConfig {
        applicationId = "net.harutiro.nationalweather"
        minSdk = 26
        targetSdk = 35
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
                "proguard-rules.pro",
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
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    lint {
        // TimberのLogを使わないため、TimberのLintを無効化
        this.disable +=
            mutableListOf(
                "TimberArgCount",
                "TimberArgTypes",
                "TimberTagLength",
                "BinaryOperationInTimber",
                "LogNotTimber",
                "StringFormatInTimber",
                "ThrowableNotAtBeginning",
            )
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.navigation.compose)
    implementation(libs.runtime.livedata)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    implementation(libs.viewmodel.compose)
    implementation(libs.material.icons.extended)
    implementation(libs.accompanist.swiperefresh)

    implementation(libs.converter.moshi)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit)
    implementation(libs.timber)
    implementation(libs.moshi.kotlin)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    testImplementation(kotlin("test"))
}
