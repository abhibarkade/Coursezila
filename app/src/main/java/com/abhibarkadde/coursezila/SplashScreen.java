package com.abhibarkadde.coursezila;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.abhibarkadde.coursezila.auth.SignIn;
import com.abhibarkadde.coursezila.user.Home;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(() -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null)
                startActivity(new Intent(SplashScreen.this, Home.class));
            else
                startActivity(new Intent(SplashScreen.this, SignIn.class));
            finish();
        }, 1000);
    }
}