package com.abhibarkadde.coursezila.user.home_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.abhibarkadde.coursezila.R;
import com.abhibarkadde.coursezila.course.Course;
import com.abhibarkadde.coursezila.dialogs.ShowMessagePrompt;
import com.google.android.material.card.MaterialCardView;

public class FG_Featured extends Fragment {

    MaterialCardView[] courses;

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
                view.findViewById(R.id.c4)
        };

        courses[0].setOnClickListener(v ->
                startActivity(new Intent(getActivity(), Course.class)));

        for (int i = 1; i < courses.length; i++)
            courses[i].setOnClickListener(v ->
                    ShowMessagePrompt.showPrompt(getActivity(),
                            "Course not Available",
                            "Course will be available soon"));

        return view;
    }
}














