package com.abhibarkadde.coursezila.user;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.abhibarkadde.coursezila.R;
import com.abhibarkadde.coursezila.profile.UserDetails;
import com.abhibarkadde.coursezila.user.home_fragments.FG_Featured;
import com.abhibarkadde.coursezila.user.home_fragments.FG_MyLearning;
import com.abhibarkadde.coursezila.user.home_fragments.FG_Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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
            return true;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        firestore.collection("Users")
                .addSnapshotListener((value, error) -> {
                    boolean flg = false;
                    for (DocumentSnapshot snap : value.getDocuments()) {
                        UserDetails userDetails = snap.toObject(UserDetails.class);
                        if (userDetails.getName().equals(user.getDisplayName()))
                            flg = true;

                        if (!flg) {
                            UserDetails usr = new UserDetails(
                                    user.getEmail().split("@")[0],
                                    user.getEmail(),
                                    ""
                            );
                            firestore.collection("Users")
                                    .document(usr.getName())
                                    .set(usr);
                        }
                    }
                });
    }
}















