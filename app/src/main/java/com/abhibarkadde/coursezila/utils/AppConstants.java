package com.abhibarkadde.coursezila.utils;

public class AppConstants {
    public static String id = "CRS:ANDROID:V1";

    public static String[] module = new String[]{
            "Installation of Android Studio",
            "Getting started with basics of Android",
            "Media: Images, Audio & video",
            "GitHub",
            "Important concepts in Android",
            "Advanced concepts in Android",
            "SQLite Database",
            "Firebase",
            "Extra Concepts",
            "Map & Geolocation"
    };

    public static String[][] lectures = new String[][]{
            {
                    "Download and Install Android Studio ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F1.Installation%20of%20Android%20Studio%2F1.Download%20and%20install.mp4?alt=media&token=717b5e1a-6d35-4723-8152-3855a9af28e0",
                    "First App 'Hello World' ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F1.Installation%20of%20Android%20Studio%2F2.first%20app.mp4?alt=media&token=797694ef-6a04-4f66-b106-fff2143d1d83",
                    "Setup AVD and Run app ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F1.Installation%20of%20Android%20Studio%2F3.avd%20setup.mp4?alt=media&token=00c83110-5a03-4ff7-bb4c-2dae3ceb900e",
                    "Run app on physical device : Wired ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F1.Installation%20of%20Android%20Studio%2F4.physical%20run.mp4?alt=media&token=62ad4766-8cb5-47fe-8fd6-7e3d4e0a2ff8",
                    "Run app on physical device : Wireless ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F1.Installation%20of%20Android%20Studio%2F5.adb.mp4?alt=media&token=82533061-0e7d-4399-b9a2-66f91251ebeb",
                    "Extra option : Bluestacks ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F1.Installation%20of%20Android%20Studio%2F6.bluestacks.mp4?alt=media&token=187142b1-2208-471c-953f-a71bd4bd2b41",
                    "App basics files ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F1.Installation%20of%20Android%20Studio%2F7.files%20basics.mp4?alt=media&token=64b6e3c1-2d20-46ce-a5d9-b41cf095f92f"
            },
            {
                    "View ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F2.Getting%20started%20with%20basics%20of%20Android%2Fview.mp4?alt=media&token=7d2f5d02-df86-47f7-8292-3e7bbbee6b8b",
                    "Layout ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F2.Getting%20started%20with%20basics%20of%20Android%2Flayouts%20intro.mp4?alt=media&token=bd65068d-1e32-4d27-8aa7-ac016dd2817a",
                    "TextView ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F2.Getting%20started%20with%20basics%20of%20Android%2Ftextview.mp4?alt=media&token=082e7861-cfd2-4cfb-8960-96dbdcb66b0f",
                    "Button ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F2.Getting%20started%20with%20basics%20of%20Android%2FButton.mp4?alt=media&token=e9a4820f-6c58-4e28-8634-bf918f159304",
                    "EdiText ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F2.Getting%20started%20with%20basics%20of%20Android%2Fedittext.mp4?alt=media&token=cae5bb93-fed2-447e-8af4-2268ca66b61c",
                    "App : Calculate area of rectangle ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F2.Getting%20started%20with%20basics%20of%20Android%2Farea%20of%20rectangle.mp4?alt=media&token=91409696-0d39-4ba8-ad4a-39b6d28fef9e",
                    "Adding ,Opening new Activities & Data Passing between them ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F2.Getting%20started%20with%20basics%20of%20Android%2Fmutiple%20activities.mp4?alt=media&token=a3cb3098-03e2-48fc-9fd3-6fbe35a9c0ec",
                    "App Bar ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F2.Getting%20started%20with%20basics%20of%20Android%2Ftoolbar%20name.mp4?alt=media&token=cf4aca04-5b04-438d-9b57-680427aa0359",
                    "WRAP_CONTENT & MATCH_PARENT ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F2.Getting%20started%20with%20basics%20of%20Android%2Fmatch_wrap_diff.mp4?alt=media&token=f16c26db-96d7-405e-98ee-905258f00d84",
                    "SnackBar ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F2.Getting%20started%20with%20basics%20of%20Android%2Fsnackbar.mp4?alt=media&token=01386752-f69f-4be8-8c50-637b20f159ae",
                    "LinearLayout, FrameLayout & RelativeLayout ~ https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F2.Getting%20started%20with%20basics%20of%20Android%2Flayouts%20more.mp4?alt=media&token=40c78ae7-33c8-4ddf-89b4-527ee4c2a0cc"
            },
    };
}
