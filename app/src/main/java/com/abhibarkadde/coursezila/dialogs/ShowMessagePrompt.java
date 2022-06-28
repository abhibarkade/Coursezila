package com.abhibarkadde.coursezila.dialogs;

import androidx.fragment.app.FragmentActivity;

import com.abhibarkadde.coursezila.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ShowMessagePrompt {

    public static void showPrompt(FragmentActivity activity, String title, String msg) {
        new MaterialAlertDialogBuilder(activity, R.style.MaterialAlertDialog_rounded)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss())
                .create()
                .show();
    }
}
