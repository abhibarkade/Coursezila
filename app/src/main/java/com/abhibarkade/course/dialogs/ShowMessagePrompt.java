package com.abhibarkade.course.dialogs;

import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.abhibarkade.course.R;
import com.abhibarkade.course.profile.UserDetails;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowMessagePrompt {

    public static void showPrompt(FragmentActivity activity, String title, String msg) {
        new MaterialAlertDialogBuilder(activity, R.style.MaterialAlertDialog_rounded)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss())
                .create()
                .show();
    }

    public static void showPrompt(AppCompatActivity activity, String title, String msg) {
        new MaterialAlertDialogBuilder(activity, R.style.MaterialAlertDialog_rounded)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss())
                .create()
                .show();
    }

    public static void showRegistrationPrompt(FragmentActivity activity, String title, String msg, FirebaseFirestore firestore, FirebaseUser user) {
        EditText editText = new EditText(activity);
        editText.setMaxLines(1);
        editText.setHint("Coupon Code");

        new MaterialAlertDialogBuilder(activity, R.style.MaterialAlertDialog_rounded)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setView(editText)
                .setPositiveButton("Continue", (dialogInterface, i) -> {
                    if (editText.getText().toString().trim().equals("letmein@2022")) {

                    } else
                        showPrompt(activity, "Incorrect Coupon code", "Enter a valid coupon code to get started with it");
                    dialogInterface.dismiss();
                })
                .create()
                .show();
    }

    public static void showRegistrationPrompt(AppCompatActivity activity, String title, String msg, FirebaseFirestore firestore, FirebaseUser user) {
        EditText editText = new EditText(activity);
        editText.setMaxLines(1);
        editText.setHint("Coupon Code");

        new MaterialAlertDialogBuilder(activity, R.style.MaterialAlertDialog_rounded)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setView(editText)
                .setPositiveButton("Continue", (dialogInterface, i) -> {
                    if (editText.getText().toString().trim().equals("123")) {
                        addUserForCourse(activity, firestore, user);
                    } else
                        showPrompt(activity, "Incorrect Coupon code", "Enter a valid coupon code to get started with it");
                    dialogInterface.dismiss();
                })
                .create()
                .show();
    }

    private static void addUserForCourse(AppCompatActivity activity, FirebaseFirestore firestore, FirebaseUser user) {
        firestore.collection("Users")
                .document(user.getEmail().split("@")[0])
                .addSnapshotListener((value, error) -> {
                    UserDetails userDetails = value.toObject(UserDetails.class);
                    assert userDetails != null;
                    userDetails.setEnrolledIn("CRS:ANDROID:V1");
                    firestore.collection("Users")
                            .document(user.getEmail().split("@")[0])
                            .set(userDetails).addOnSuccessListener(unused -> {
                                showPrompt(activity
                                        , "Registered Successfully",
                                        "Continue to go to course");
                            }).addOnFailureListener(e -> Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show());
                });
    }
}
