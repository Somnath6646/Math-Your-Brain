-keep class com.wenull.mathyourbrain.models.*{ *; }
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault

-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*

#-keep class com.google.gson.stream.** { *; }