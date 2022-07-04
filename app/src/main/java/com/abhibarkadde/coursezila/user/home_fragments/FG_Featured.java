package com.abhibarkadde.coursezila.user.home_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.abhibarkadde.coursezila.AboutUs;
import com.abhibarkadde.coursezila.R;
import com.abhibarkadde.coursezila.course.Course;
import com.abhibarkadde.coursezila.dialogs.ShowMessagePrompt;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class FG_Featured extends Fragment {

    MaterialCardView[] courses;
    ImageView mic;
    EditText edSearch;
    MaterialCardView aboutUs;

    public FG_Featured() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_f_g__home, container, false);

        courses = new MaterialCardView[]{
                view.findViewById(R.id.c1),
                view.findViewById(R.id.c2),
                view.findViewById(R.id.c3),
                view.findViewById(R.id.c4),
                view.findViewById(R.id.c6),
                view.findViewById(R.id.c5)
        };

        aboutUs = view.findViewById(R.id.openAboutUs);
        aboutUs.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), AboutUs.class));
        });

        edSearch = view.findViewById(R.id.ed_search);
        mic = view.findViewById(R.id.im_mic);
        mic.setOnClickListener(v -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");
            try {
                startActivityForResult(intent, 101);
            } catch (Exception e) {
                Log.d("TAG", e.getMessage());
            }
        });

        courses[0].setOnClickListener(v ->
                startActivity(new Intent(getActivity(), Course.class)));

        for (int i = 1; i < courses.length; i++)
            courses[i].setOnClickListener(v ->
                    ShowMessagePrompt.showPrompt(getActivity(),
                            "Course not Available",
                            "Course will be available soon"));

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                edSearch.setText(Objects.requireNonNull(result).get(0));
            }
        }
    }
}














