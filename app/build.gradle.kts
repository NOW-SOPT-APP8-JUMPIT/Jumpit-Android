plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.sopt.now.jumpit"
    compileSdk = libs.versions.targetSdk.get().toInt()

    defaultConfig {
        applicationId = "com.sopt.now.jumpit"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = libs.versions.configVersion.toString()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", getApiKey("base.url"))
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
        buildConfig = true
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    // AndroidX
    implementation(libs.bundles.androidx)

    // Test
    testImplementation(libs.bundles.test)

    // Material
    implementation(libs.bundles.material)

    // ktx
    implementation(libs.bundles.ktx)

    // Retrofit2
    implementation(libs.retrofit2)

    // Serialization
    implementation(libs.bundles.serialization)

    // BOM
    implementation(platform(libs.bom))

    // Okhttp3
    implementation(libs.bundles.okhttps3)

    // ViewPager2
    implementation(libs.viewpager2)

    // Coroutine
    implementation(libs.coroutine)

    // ViewModel
    implementation(libs.viewmodel)

    // Coil
    implementation(libs.bundles.coil)

    // Navigation
    implementation (libs.bundles.navigation)
}