
-dontwarn com.squareup.okhttp3.**
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8 #Retain generic type information for use by reflection by converters and adapters
-dontnote retrofit2.Platform #Platform used when running on Java8 VMs.Will not be used at runtime.
-keepattributes Signature #retain declared checked exceptions for use by a Proxy instance
-keepattributes Exceptions
-keepattributes Annotation
-keep class okhttp3.*{*;}
-keep interface okhttp3.*{*;}
-dontwarn okhttp3
-ignorewarnings
-keep class * {public private *;}
-dontwarn uk.**
-keep class retrofit.** { *; }
-keep class com.google.gson.**{*;}
-keep public class com.google.gson.**{public private protected *;}
-keep class com.exampleapp.model.** { *; }
-keepclassmembers class android.support.design.internal.BottomNavigationMenuView {
    boolean mShiftingMode;
}
-keep class com.technocracy.app.aavartan.** { *; }
-keep public class com.google.gson