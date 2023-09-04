-optimizationpasses 3
-overloadaggressively
-repackageclasses ''
-allowaccessmodification

-keep class app.cash.sqldelight.driver.jdbc.sqlite.** { *; }
-keep class org.sqlite.** { *; }
-keep class kotlin.** { *; }
-keep class org.jetbrains.skia.** { *; }
-keep class org.jetbrains.skiko.** { *; }

# Kotlinx Coroutines Rules
# https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-core/jvm/resources/META-INF/proguard/coroutines.pro

-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}
-dontwarn java.lang.instrument.ClassFileTransformer
-dontwarn sun.misc.SignalHandler
-dontwarn java.lang.instrument.Instrumentation
-dontwarn sun.misc.Signal
-dontwarn java.lang.ClassValue
-dontwarn org.slf4j.**
-dontwarn net.folivo.trixnity.**
