package br.senai.sp.cotia.tictactoeapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.senai.sp.cotia.tictactoeapp.R;
import br.senai.sp.cotia.tictactoeapp.databinding.FragmentInicioBinding;

public class inicioFragment extends Fragment {

    private FragmentInicioBinding binding;
    private EditText  nomeJog1, nomeJog2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        binding.botaoInicio.setOnClickListener(V ->{
            NavHostFragment.findNavController(inicioFragment.this).navigate(R.id.action_inicioFragment_to_jogoFragment);
        });


        return binding.getRoot();
    }

    public void onStart() {
        super.onStart();
        //pega a referencia para activity
        AppCompatActivity minhaActivity = (AppCompatActivity) getActivity();
        minhaActivity.getSupportActionBar().hide();

    }
}