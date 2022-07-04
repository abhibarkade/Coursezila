package com.abhibarkadde.coursezila;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Objects;

public class AboutUs extends AppCompatActivity {

    MaterialToolbar toolbar;

    String aboutus = "Welcome to Coursezila,\\n An application that provides various software development courses. We are dedicated to providing you with the best learning and coaching, focusing on well understanding.\\n\\n\n" +
            "            We are working to turn our passion into a great course. We hope you are enjoying our courses as much as we are enjoying by offering them to you.\\n\\n\n" +
            "            Students can consider these courses to learn software development for several platforms like android, windows, web etc.\\n\\n\n" +
            "            Our application's main motive is to provide personal teaching courses to students in various fields.";

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        toolbar = findViewById(R.id.course_toolbar);
        {
            toolbar.setTitle("About Us");
            toolbar.setTitleCentered(true);
            setSupportActionBar(toolbar);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        txt = findViewById(R.id.txt);
        //txt.setText(aboutus);
    }
}