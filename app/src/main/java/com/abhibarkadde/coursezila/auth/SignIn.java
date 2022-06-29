package com.abhibarkadde.coursezila.auth;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.abhibarkadde.coursezila.R;

public class SignIn extends AppCompatActivity {

    TextView txtSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Init
        txtSignIn = findViewById(R.id.txtSignIn);
    }
}