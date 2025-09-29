plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    jvmToolchain(jdkVersion = 17)
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)
    implementation(libs.coroutines.core)
    implementation(libs.moshi.core)
    implementation(libs.javax.inject)

    testImplementation(libs.bundles.test.base)
}
