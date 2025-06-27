# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

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
#-renamesourcefileattribute SourceFile

# Keep Room annotations and related classes
-keep class androidx.room.** { *; }

# Keep entities, DAOs, and database classes
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao class * { *; }
-keep @androidx.room.Database class * { *; }

# Keep fields annotated with Room annotations
-keepclassmembers class * {
    @androidx.room.PrimaryKey <fields>;
    @androidx.room.ColumnInfo <fields>;
    @androidx.room.Ignore <fields>;
}

# Optional: If using Paging with Room
-dontwarn androidx.room.paging.**

# Preserve generic type info and annotations
-keepattributes Signature, *Annotation*, EnclosingMethod

# Keep Gson core classes
-keep class com.google.gson.stream.** { *; }

# Keep custom adapters and serializers
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# If using @SerializedName
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Keep Retrofit annotations and method signatures
-keepattributes Signature, InnerClasses, EnclosingMethod, AnnotationDefault, RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep Retrofit interfaces
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-keepclassmembers interface * {
    @retrofit2.http.* <methods>;
}

# Avoid warnings
-dontwarn retrofit2.**
-dontwarn kotlin.Unit

# Keep Hilt ViewModels and generated code
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.lifecycle.HiltViewModel { *; }

# Keep Dagger-generated classes
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory { *; }
-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }

# Avoid warnings
-dontwarn dagger.hilt.**
-dontwarn javax.inject.**

# Keep Compose runtime and UI internals
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Prevent crashes related to text layout
-keepclassmembers class androidx.compose.ui.platform.AndroidComposeView {
    android.view.View findViewByAccessibilityIdTraversal(int);
}
-keepclassmembers class androidx.compose.ui.platform.ViewLayerContainer {
    protected void dispatchGetDisplayList();
}

# Modifier.Node reflection safety
-keep,allowshrinking class * extends androidx.compose.ui.node.ModifierNodeElement