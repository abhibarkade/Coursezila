package com.abhibarkadde.coursezila.admin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class AddCourse extends AppCompatActivity {

    EditText title, subtitle, lang, creater, disc;
    MaterialButton btn;
    ImageView thumbnail;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        db = FirebaseFirestore.getInstance();

        title = findViewById(R.id.ed_title);
        subtitle = findViewById(R.id.ed_Subtitle);
        creater = findViewById(R.id.ed_creater);
        disc = findViewById(R.id.ed_Disc);
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

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child("Courses")
                .child("C:adc01373-2a2a-4c4e-94ff-ea79d1a65998")
                .child("thumbnail.jpg")
                .getDownloadUrl()
                .addOnSuccessListener(uri -> Picasso.get().load(uri).into(thumbnail)).addOnFailureListener(e -> Toast.makeText(AddCourse.this, e.getMessage(), Toast.LENGTH_SHORT).show());
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
//        String id = "C:" + UUID.randomUUID();
        CourseBasics basics = new CourseBasics(
                "", "", "", "", "", "", ""
        );
/*
        Bitmap bitmap = ((BitmapDrawable) thumbnail.getDrawable()).getBitmap();

        StorageReference storage = FirebaseStorage.getInstance().getReference();
        storage.child("Courses")
                .child(id)
                .child("Thumbnail.png")
                .putBytes(bitmap.getNinePatchChunk())
                .addOnFailureListener(e -> Toast.makeText(AddCourse.this, e.getMessage(), Toast.LENGTH_SHORT).show())
                .addOnSuccessListener(taskSnapshot -> {
                    basics.setProfileUrl(String.valueOf(taskSnapshot.getStorage().getDownloadUrl()));
                    Toast.makeText(this, "Thumbnail Added", Toast.LENGTH_SHORT).show();
                });*/

        /*db.collection("Courses")
                .document("C:adc01373-2a2a-4c4e-94ff-ea79d1a65998")
                .collection("Modules")
                .add(new ModuleAdd("Module 1"));*/

        db.collection("Courses")
                .document("C:adc01373-2a2a-4c4e-94ff-ea79d1a65998")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        CourseBasics basics1 = value.toObject(CourseBasics.class);
                        Toast.makeText(AddCourse.this, basics1.toString(), Toast.LENGTH_SHORT).show();
                    }
                });



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
}



















