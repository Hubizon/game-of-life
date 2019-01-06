# By default, the flags in this file are appended to flags specified
# in C:\Users\Hubert\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile




# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# The following rules are used to strip any non essential Google Play Services classes and method.

-repackageclasses 'o'
-dontusemixedcaseclassnames
-dontpreverify
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-verbose
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification

-keepclassmembers class com.howky.brothers.lifeonsteroids.education.Skill {
        <fields>;
}
-keepclassmembers class com.howky.brothers.lifeonsteroids.work.Job {
        <fields>;
}
-keepclassmembers class com.howky.brothers.lifeonsteroids.work.Job {
        public <init>();
}
-keepclassmembers class com.howky.brothers.lifeonsteroids.work.CriminalJob {
        <fields>;
}
-keepclassmembers class com.howky.brothers.lifeonsteroids.work.CriminalJob {
        public <init>();
}
-keepclassmembers class com.howky.brothers.lifeonsteroids.shop.Weapon {
        <fields>;
}
-keepclassmembers class com.howky.brothers.lifeonsteroids.shop.Weapon {
        public <init>();
}
-keepclassmembers class com.howky.brothers.lifeonsteroids.shop.Food {
        <fields>;
}
-keepclassmembers class com.howky.brothers.lifeonsteroids.shop.Food {
        public <init>();
}
-keepclassmembers class com.howky.brothers.lifeonsteroids.shop.Lottery {
        <fields>;
}
-keepclassmembers class com.howky.brothers.lifeonsteroids.shop.Lottery {
        public <init>();
}
-keepclassmembers class com.howky.brothers.lifeonsteroids.shop.Medicines {
        <fields>;
}
-keepclassmembers class com.howky.brothers.lifeonsteroids.shop.Medicines {
        public <init>();
}


# For Google Play Services
-keep public class com.google.android.gms.ads.**{
   public *;
}

# For old ads classes
-keep public class com.google.ads.**{
   public *;
}

# For mediation
-keepattributes *Annotation*

# Other required classes for Google Play Services
# Read more at http://developer.android.com/google/play-services/setup.html
-keep class * extends java.util.ListResourceBundle {
   protected Object[][] getContents();
}



##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

##---------------End: proguard configuration for Gson  ----------