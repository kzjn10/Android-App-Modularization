plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.anhndt.quote"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.anhndt.quote"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

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

    buildFeatures {
        compose = true
    }

//    dynamicFeatures.add(":dynamicfeature:df1")

    androidResources {
        generateLocaleConfig = true
    }
}

dependencies {

    // Common
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.material3)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3.adaptive.navigation.suite)

    // Support Auto Store Locale for android 12 and lower
    implementation(libs.androidx.appcompat)

    // Hilt - DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Firebase
    implementation(platform(libs.firebase.bom))

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // For dynamic features
//    implementation(libs.core)
//    implementation(libs.asset.delivery)
//    implementation(libs.feature.delivery)
//    implementation(libs.review)
//    implementation(libs.app.update)


    // Modules
    implementation(project(":core:designsystem"))
    implementation(project(":core:common"))
    implementation(project(":core:l10n"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    implementation(project(":features:quotes"))
    implementation(project(":features:quotedetail"))
    implementation(project(":features:favorite"))
    implementation(project(":features:profile"))

//    implementation(project(":dynamicfeature:df1"))
}

kapt {
    correctErrorTypes = true
}