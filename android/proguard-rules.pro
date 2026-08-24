-dontobfuscate

-keep class mindustry.** { *; }
-keep class arc.** { *; }
-keep class net.jpountz.** { *; }
-keep class rhino.** { *; }
-keep class com.android.dex.** { *; }
-keep class com.android.dx.** { *; }
-keepattributes Signature,*Annotation*,InnerClasses,EnclosingMethod

-dontwarn javax.naming.**

# Suppress missing JVM-specific classes used for desktop
# AGP is no longer lenient about missing classes
-dontwarn java.lang.management.**
-dontwarn javax.script.**
-dontwarn org.bouncycastle.**

#-printusage out.txt