package com.abhibarkadde.coursezila.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.abhibarkadde.coursezila.R;
import com.abhibarkadde.coursezila.user.Home;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;

public class SignIn extends AppCompatActivity {

    protected TextView txtSignIn;
    protected ConstraintLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Init
        root = findViewById(R.id.signInRoot);

        txtSignIn = findViewById(R.id.txtSignIn);
        txtSignIn.setOnClickListener(v -> signInWithGoogle());
    }

    private void signInWithGoogle() {
        List<AuthUI.IdpConfig> providers = Collections.singletonList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(false)
                .build();
        signInLauncher.launch(signInIntent);
    }

    @SuppressLint("NewApi")
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        if (result.getResultCode() == RESULT_OK) {
            startActivity(new Intent(SignIn.this, Home.class));
            finish();
        } else
            Snackbar.make(root, "Sign in Failed", 1600)
                    .setBackgroundTint(getColor(R.color.white))
                    .setAction("Try Again", view -> signInWithGoogle())
                    .setActionTextColor(getColor(R.color.blue))
                    .show();
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            this::onSignInResult
    );
}