# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/hades/Android/Sdk/tools/proguard/proguard-android.txt
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
-keep public class cn.waps.** {*;}
-keep public interface cn.waps.** {*;}
#对亍使用 4.0.3 以上 android-sdk 进行顷目编译时产生异常癿情况时,加入以下内容:
-dontwarn cn.waps.**