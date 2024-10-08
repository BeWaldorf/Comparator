// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.android.application") version "8.3.0-alpha18" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0" //apply false
    //id("com.google.dagger.hilt.android") version "2.44" apply false
}