
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.spiritual.brihaspativarkatha"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.spiritual.brihaspativarkatha"
        minSdk = 24
        targetSdk = 36
        versionCode = 9
        versionName = "1.7"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        create("release") {
            storeFile = file("../keystore/Brihaspativar.jks")
            storePassword = "Admin123@"
            keyAlias = "brihaspativar"
            keyPassword = "Admin123@"
        }
    }

    buildTypes {
        getByName("debug") {
            buildConfigField(
                "String",
                "ADMOB_INTERSTITIAL_ID",
                "\"${project.findProperty("ADMOB_INTERSTITIAL_DEBUG")}\""
            )
        }
        getByName("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            buildConfigField(
                "String",
                "ADMOB_INTERSTITIAL_ID",
                "\"${project.findProperty("ADMOB_INTERSTITIAL_RELEASE")}\""
            )

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
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.play.app.update)
    implementation(libs.play.app.update.ktx)
    implementation(libs.coroutines.play.services)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.google.ads)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}