plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktlint)
}

kotlin {
    jvmToolchain(jdkVersion = 17)
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp.logging)
    implementation(libs.coroutines.core)
    implementation(libs.moshi.kotlin)
    implementation(libs.javax.inject)

    testImplementation(libs.bundles.unitTest)
}
