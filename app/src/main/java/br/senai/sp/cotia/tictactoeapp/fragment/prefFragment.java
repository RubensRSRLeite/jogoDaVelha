package br.senai.sp.cotia.tictactoeapp.fragment;

import android.os.Bundle;



import br.senai.sp.cotia.tictactoeapp.R;

import androidx.preference.PreferenceFragmentCompat;


public class prefFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }
}