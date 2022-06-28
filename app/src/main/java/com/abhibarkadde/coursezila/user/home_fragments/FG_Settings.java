package com.abhibarkadde.coursezila.user.home_fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.abhibarkadde.coursezila.R;

public class FG_Settings extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}