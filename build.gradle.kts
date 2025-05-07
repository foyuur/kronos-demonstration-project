plugins {
    alias { libs.plugins.kotlin.jvm }
    alias { libs.plugins.kotlin.allopen }
    alias { libs.plugins.kronos.gradle.plugin }
}

dependencies {
    testImplementation(libs.kotlin.test)
    implementation(libs.driver.jdbc.mysql)
    implementation(libs.druid)
    implementation(libs.kronos.core)
    implementation(libs.kronos.jdbc.wrapper)
}
