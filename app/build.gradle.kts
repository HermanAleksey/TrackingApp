import org.jetbrains.kotlin.konan.properties.Properties

import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version LibVersions.ksp_version // Depends on your kotlin version

    id("org.jlleitschuh.gradle.ktlint")
}

@Suppress("UnstableApiUsage")
android {
    namespace = "com.justparokq.parokq_tracker"
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionName = Config.VERSION_NAME
        versionCode = Config.VERSION_CODE

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    val propFile = File("./signing.properties")
    println(propFile.exists())

    if (propFile.exists()) {
        val props = Properties()
        props.load(FileInputStream(propFile))

        signingConfigs {
            create("release") {
                storeFile = file(props["STORE_FILE"] ?: "")
                keyAlias = props["KEY_ALIAS"].toString()
                keyPassword = props["KEY_PASSWORD"].toString()
                storePassword = props["STORE_PASSWORD"].toString()
            }
        }
        defaultConfig {
            signingConfig = signingConfigs.getByName("release")
        }
    }

    kapt {
        correctErrorTypes = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = LibVersions.compose_compiler_version
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }

    lint {
        baseline = File("lint-baseline.xml")
    }
}

ktlint {
    android.set(true)
    ignoreFailures.set(false)
    disabledRules.set(
        listOf(
            "File must end with a newline (\\n)",
            "no-wildcard-imports",
            "import-ordering",
            "final-newline"
        )
    )
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:design_system"))
    implementation(project(":core:model"))
    implementation(project(":core:testing"))
    implementation(project(":feature:splash_screen"))

    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_ui_tooling_preview)
    implementation(Dependencies.activity_compose)
    implementation(Dependencies.compose_material_icons)

    // animations
    implementation(Dependencies.accompanist_system_ui_controller)

    // navigation
    implementation(Dependencies.raamcosta_compose_destinations_anim_core)
    ksp(Dependencies.raamcosta_compose_destinations_ksp)

    // splash screen
    implementation(Dependencies.splash_screen_core)

    // hilt
    implementation(Dependencies.hilt_navigation_compose)
    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)

    implementation(Dependencies.timber)
}
