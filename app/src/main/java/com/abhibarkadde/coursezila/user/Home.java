package com.abhibarkadde.coursezila.user;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.abhibarkadde.coursezila.R;
import com.abhibarkadde.coursezila.user.home_fragments.FG_Featured;
import com.abhibarkadde.coursezila.user.home_fragments.FG_MyLearning;
import com.abhibarkadde.coursezila.user.home_fragments.FG_Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    BottomNavigationView navigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigationView = findViewById(R.id.bottom_nav_view);

        // Default Fragment
        getSupportFragmentManager().beginTransaction().add(R.id.root, new FG_Featured()).commit();

        navigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.menu_featured:
                    fragment = new FG_Featured();
                    break;
                case R.id.menu_mylearning:
                    fragment = new FG_MyLearning();
                    break;
                case R.id.menu_setting:
                    fragment = new FG_Settings();
                    break;
            }
            assert fragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.root, fragment).commit();
            return false;
        });


    }
}















