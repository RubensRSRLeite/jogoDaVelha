package br.senai.sp.cotia.tictactoeapp.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;
import java.util.zip.Inflater;

import br.senai.sp.cotia.tictactoeapp.R;
import br.senai.sp.cotia.tictactoeapp.databinding.FragmentInicioBinding;
import br.senai.sp.cotia.tictactoeapp.databinding.FragmentJogoBinding;
import br.senai.sp.cotia.tictactoeapp.util.PrefsUtil;


public class jogoFragment extends Fragment {

    // variavel para acessar os elementos da view
    private FragmentJogoBinding binding;
    //vetor de botões
    private Button[] botoes;
    //matris de string do tabuleiro
    private String[][] tabuleiro;
    //variaveis para simbolos
    private String simbJog1, simbJog2, nome1, nome2, simbolo;
    private Random random;
    // variavel para numero de jogadas
    private int numJog= 0;
    // variaveis para placar
    private int placarJog1 = 0, placarJog2 = 0, placarJog3 = 0;

    public void onStart() {
        super.onStart();
        //pega a referencia para activity
        AppCompatActivity minhaActivity = (AppCompatActivity) getActivity();
        minhaActivity.getSupportActionBar().show();
        minhaActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // chama binding

        //habilitar menu
        setHasOptionsMenu(true);

        binding = FragmentJogoBinding.inflate(inflater, container, false);

        botoes = new Button[9];

        botoes[0] = binding.bt00;
        botoes[1] = binding.bt01;
        botoes[2] = binding.bt02;
        botoes[3] = binding.bt10;
        botoes[4] = binding.bt11;
        botoes[5] = binding.bt12;
        botoes[6] = binding.bt20;
        botoes[7] = binding.bt21;
        botoes[8] = binding.bt22;

//        binding.bt00.setOnClickListener(listenerBotoes);
//        binding.bt01.setOnClickListener(listenerBotoes);
//        binding.bt02.setOnClickListener(listenerBotoes);

        //associa um listener a meu botao

        for (Button bt : botoes){
            bt.setOnClickListener(listenerBotoes);
        }

        //instancia tabuleiro
        tabuleiro = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = "";
            }
        }



        //define simbolos do jogador1 e jogador2
        simbJog1= PrefsUtil.getSimboloJog1(getContext());
        simbJog2= PrefsUtil.getSimboloJog2(getContext());

        simbJog1 = PrefsUtil.getSimboloJog1(getContext());
        simbJog2 = PrefsUtil.getSimboloJog2(getContext());
        nome1 = PrefsUtil.getNomeJog1(getContext());
        nome2 = PrefsUtil.getNomeJog2(getContext());

        binding.jogador1.setText(getResources().getString(R.string.jogador_1,simbJog1, nome1));
        binding.jogador2.setText(getResources().getString(R.string.jogador_2,simbJog2, nome2));

        //define nome do jogador 1 e jogador 2


        random = new Random();

        sorteia();
        atualizaVez();

        //retorna root da binding
        return binding.getRoot();
    }

    private void atualizaPlacar() {
        binding.placar1.setText(placarJog1+"");
        binding.placar2.setText(placarJog2+"");
        binding.placar3.setText(placarJog3+"");
    }

    private void sorteia() {
        if(random.nextBoolean()){
            simbolo = simbJog1;

        } else {
            simbolo = simbJog2;
        }
    }

    private void atualizaVez(){
        if(simbolo.equals(simbJog1)){
            binding.linear1.setBackgroundColor(getResources().getColor(R.color.pink_200));
            binding.linear2.setBackgroundColor(getResources().getColor(R.color.pink_400));
        } else {
            binding.linear1.setBackgroundColor(getResources().getColor(R.color.pink_400));
            binding.linear2.setBackgroundColor(getResources().getColor(R.color.pink_200));
        }
    }

    public boolean venceu() {
        for (int li = 0; li < 3; li++) {
            if(tabuleiro[li][0].equals(simbolo) && tabuleiro[li][1].equals(simbolo) && tabuleiro[li][2].equals(simbolo)){
                return true;
            }
        }
        for (int co = 0; co < 3; co++) {
            if(tabuleiro[0][co].equals(simbolo) && tabuleiro[1][co].equals(simbolo) && tabuleiro[2][co].equals(simbolo)){
                return true;
            }
        }

        if(tabuleiro[0][0].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][2].equals(simbolo)){
            return true;
        }

        if(tabuleiro[0][2].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][0].equals(simbolo)){
            return true;
        }



        return false;

    }



    public void limpar(){
        //limpar o visual dos botoes
        for (Button bt : botoes) {
            bt.setClickable(true);
            bt.setText("");
            bt.setBackgroundColor(getResources().getColor(R.color.teal_200));
        }
        //for para esvaziar matrix
        /*for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = "";
            }
        }*/

        for (String[] vetor : tabuleiro) {
            Arrays.fill(vetor, " ");
        }



        //zerar jogadas
        numJog = 0;
        sorteia();
        atualizaVez();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //verifica qual item foi selecionado

        switch (item.getItemId()){
            case R.id.menu_rezetar:
                AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
                alerta.setTitle(getResources().getString(R.string.dialogo_rezet));
                alerta.setMessage("caso deseja rezetar, clique em rezet").setCancelable(false)
                        .setPositiveButton("rezet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                placarJog1=0;
                                placarJog2=0;
                                placarJog3=0;
                                atualizaPlacar();
                                limpar();
                            }
                        });
                alerta.setMessage("caso deseja rezetar, clique em rezet").setCancelable(false)
                        .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                AlertDialog alertDialog = alerta.create();
                alertDialog.show();


                break;
            case R.id.menu_inicio:

                    NavHostFragment.findNavController(jogoFragment.this).navigate(R.id.action_jogoFragment_to_inicioFragment);


            case R.id.menu_pref:
                NavHostFragment.findNavController(jogoFragment.this).navigate(R.id.action_jogoFragment_to_prefFragment);
        }

        return true;
    }

    //testa velha

    // listener para os botões

    private View.OnClickListener listenerBotoes = btPress -> {
        //incrementa numero de jogadas
        numJog++;

        // obtem o nome do botão
        String nomeBotao = getContext().getResources().getResourceName(btPress.getId());

        String posicao = nomeBotao.substring(nomeBotao.length() - 2);

        //estrai linha e coluna da minha posição
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));
        tabuleiro[linha][coluna] = simbolo;
        Button botao = (Button) btPress;
        // "setta" simbolo no botão
        botao.setText(simbolo);
        //troca background
        botao.setBackgroundColor(getResources().getColor(R.color.teal_100));

        botao.setClickable(false);

        if(numJog>=5 && venceu()){
            //informa que houve um vencedor
            Toast.makeText(getContext(), R.string.parabens, Toast.LENGTH_LONG).show();

            //verifica quem venceu
            if(simbolo.equals(simbJog1)){
                placarJog1++;
            } else {
                placarJog2++;
            }

            atualizaPlacar();

            limpar();
        } else if (numJog == 9 && venceu() != true){
            Toast.makeText(getContext(), R.string.velha, Toast.LENGTH_LONG).show();
            placarJog3++;
            atualizaPlacar();
            limpar();
        } else {
            simbolo = simbolo.equals(simbJog1) ? simbJog2 : simbJog1;
            atualizaVez();
        }

    };
}