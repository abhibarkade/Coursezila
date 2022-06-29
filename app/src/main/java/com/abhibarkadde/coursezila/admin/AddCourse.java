package com.abhibarkadde.coursezila.admin;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abhibarkadde.coursezila.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AddCourse extends AppCompatActivity {

    EditText title, subtitle, lang, creater, disc, module;
    MaterialButton btn;
    ImageView thumbnail;

    FirebaseFirestore db;

    public static String convertMillieToHMmSs(long millie) {
        long seconds = (millie / 1000);
        long second = seconds % 60;
        long minute = (seconds / 60) % 60;
        long hour = (seconds / (60 * 60)) % 24;

        String result = "";
        if (hour > 0) {
            return String.format("%02d:%02d:%02d", hour, minute, second);
        } else {
            return String.format("%02d:%02d", minute, second);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        db = FirebaseFirestore.getInstance();


        title = findViewById(R.id.ed_title);
        subtitle = findViewById(R.id.ed_Subtitle);
        creater = findViewById(R.id.ed_creater);
        disc = findViewById(R.id.ed_Disc);
        module = findViewById(R.id.ed_module);
        lang = findViewById(R.id.ed_lang);
        thumbnail = findViewById(R.id.im_thumbnail);
        btn = findViewById(R.id.btn_images);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
        }

        btn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("images/*");
            startActivityForResult(Intent.createChooser(intent, "Select Thumbnail"), 102);
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 102 && data != null) {
            Picasso.get().load(data.getData()).into(thumbnail);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
        }
    }

    public void createCourese(View view) {
        String id = "CRS:ANDROID:V1";

        /*CourseBasics basics = new CourseBasics(
                "Android App Development Bootcamp with Java",
                "Learn Android App Development using Java in HINDI- Build real apps with Android Studio",
                "No Description Found",
                "Abhishek Barkade",
                "Hindi",
                "",
                id
        );
        db.collection("Courses")
                .document(id)
                .collection("CourseInfo")
                .document("Info")
                .set(basics);
        Toast.makeText(this, "Course Added", Toast.LENGTH_SHORT).show();

        String[] list = new String[]{
                "1.Installation of Android Studio",
                "2.Getting started with basics of Android Studio",
                "3.Media: Images, Audio and Video",
                "4.GitHub",
                "5.Important concepts in Android",
                "6.Advanced concepts in Android",
                "7.SQLite Database",
                "8.Firebase",
                "9.Extra Concepts",
                "10.Map & Geolocation"
        };
*/
        String[] l2 = new String[]{
                "1.Download & Install Android Studio",
                "2.First App 'Hello World'",
                "3.Setup AVD & Run app",
                "4.Run app on physical device : Wired",
                "5.Run app on physical device : Wireless",
                "6.Extra options : Bluestacks",
                "7.App basic files"
        };

/*        for (String str : l2) {
            db.collection("Courses")
                    .document(id)
                    .collection("Modules")
                    .document("1.Installation of Android Studio")
                    .collection("1.Installation of Android Studio")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            Toast.makeText(AddCourse.this, "" + value.size(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }*/

        /*for (int j = 1; j <= 5; j++) {
            for (int i = 1; i <= 5; i++) {
                db.collection("Courses")
                        .document(id)
                        .collection("Modules")
                        .document("Module" + j)
                        .collection("Lecture" + i)
                        .document("Lecture" + i)
                        .set(new LectureDetails());
            }
        }*/

        /*db.collection("Courses")
                .document(id)
                .collection("Modules")
                .addSnapshotListener((value, error) -> {
                    Toast.makeText(this, value.toString(), Toast.LENGTH_SHORT).show();
                });*/



/*
        List<LectureDetails> details = new ArrayList<>();
        StorageReference ref = FirebaseStorage.getInstance().getReference();
        ref.child("Courses")
                .child(id)
                .child("1.Installation of Android Studio")
                .listAll()
                .addOnSuccessListener(listResult -> {
                    List<StorageReference> refs = listResult.getItems();
                    for (int i = 0; i < refs.size(); i++) {
                        LectureDetails tmp = new LectureDetails();
                        tmp.setName(refs.get(i).getName());
                        int finalI = i;
                        refs.get(i).getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        tmp.setLink(String.valueOf(uri));
                                        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                                        retriever.setDataSource(String.valueOf(uri), new HashMap<String, String>());
                                        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                                        long timeInMillisec = Long.parseLong(time);
                                        long seconds = (timeInMillisec / 1000);
                                        long second = seconds % 60;
                                        long minute = (seconds / 60) % 60;
                                        long hour = (seconds / (60 * 60)) % 24;

                                        String result = "";
                                        if (hour > 0)
                                            result = String.format("%02d:%02d:%02d", hour, minute, second);
                                        else
                                            result = String.format("%02d:%02d", minute, second);

                                        retriever.release();

                                        tmp.setLength(result);

                                        {
                                            db.collection("Courses")
                                                    .document(id)
                                                    .collection("Modules")
                                                    .document("1.Installation of Android Studio")
                                                    .collection("1.Installation of Android Studio")
                                                    .document(l2[finalI])
                                                    .collection(l2[finalI])
                                                    .document(l2[finalI])
                                                    .set(tmp);
                                        }
                                    }
                                });
                    }

                });

        Toast.makeText(this, "" + details.size(), Toast.LENGTH_SHORT).show();
*/


        /*Bitmap bitmap = ((BitmapDrawable) thumbnail.getDrawable()).getBitmap();
        StorageReference storage = FirebaseStorage.getInstance().getReference();
        storage.child("Courses")
                .child(id)
                .child("Raw")
                .child("Thumbnail.png")
                .putBytes(bitmap.getNinePatchChunk())
                .addOnFailureListener(e -> Toast.makeText(AddCourse.this, e.getMessage(), Toast.LENGTH_SHORT).show())
                .addOnSuccessListener(taskSnapshot -> {

                });*/

        /*db.collection("Courses")
                .document("C:adc01373-2a2a-4c4e-94ff-ea79d1a65998")
                .collection("Modules")
                .add(new ModuleAdd("Module 1"));*/

        /*db.collection("Courses")
                .document("C:adc01373-2a2a-4c4e-94ff-ea79d1a65998")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        CourseBasics basics1 = value.toObject(CourseBasics.class);
                        Toast.makeText(AddCourse.this, basics1.toString(), Toast.LENGTH_SHORT).show();
                    }
                });*/



       /* db.collection("Courses")
                .document("C:adc01373-2a2a-4c4e-94ff-ea79d1a65998")
                .collection("Modules")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        Toast.makeText(AddCourse.this, "" + value.size(), Toast.LENGTH_SHORT).show();
                    }
                });*/
    }

    @SuppressLint("DefaultLocale")
    public String getVideoLength(String link) {
        String result = null;
        try {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(link, new HashMap<String, String>());
            String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            long timeInMillisec = Long.parseLong(time);
            long seconds = (timeInMillisec / 1000);
            long second = seconds % 60;
            long minute = (seconds / 60) % 60;
            long hour = (seconds / (60 * 60)) % 24;

            result = "";
            if (hour > 0)
                result = String.format("%02d:%02d:%02d", hour, minute, second);
            else
                result = String.format("%02d:%02d", minute, second);

            retriever.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addModule(View view) {
        /*db.collection("Courses")
                .document("C:adc01373-2a2a-4c4e-94ff-ea79d1a65998")
                .collection("Modules")
                .document(module.getText().toString().trim())
                .set(new ModuleAdd(module.getText().toString().trim()));*/
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child("Courses")
                .child("C:adc01373-2a2a-4c4e-94ff-ea79d1a65998")
                .child("Lectures")
                .child("1.Installation of Android Studio")
                .listAll()
                .addOnFailureListener(e -> Toast.makeText(AddCourse.this, "No Data Found!!", Toast.LENGTH_SHORT).show())
                .addOnSuccessListener(listResult -> {
                    for (StorageReference ref : listResult.getItems()) {
                        LectureDetails details = new LectureDetails(ref.getName());
                        ref.getDownloadUrl()
                                .addOnSuccessListener(uri -> {
                                    details.setLink(String.valueOf(uri));
                                    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                                    retriever.setDataSource(details.getLink(), new HashMap<String, String>());
                                    String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                                    long timeInMillisec = Long.parseLong(time);
                                    retriever.release();
                                    details.setLength(convertMillieToHMmSs(timeInMillisec));
                                    Toast.makeText(this, details.toString(), Toast.LENGTH_SHORT).show();

                                    // Add to Firestore
                                    db.collection("Courses")
                                            .document("C:adc01373-2a2a-4c4e-94ff-ea79d1a65998")
                                            .collection("Modules")
                                            .document("1.Installation of Android Studio")
                                            .collection(details.getName())
                                            .document(details.getName())
                                            .set(details);
                                })
                                .addOnFailureListener(e -> details.setLink(""));
                    }
                    Toast.makeText(this, "" + listResult.getItems().size(), Toast.LENGTH_SHORT).show();
                });
    }
}



















