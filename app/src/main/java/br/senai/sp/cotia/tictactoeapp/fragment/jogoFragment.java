package br.senai.sp.cotia.tictactoeapp.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;
import java.util.zip.Inflater;

import br.senai.sp.cotia.tictactoeapp.R;
import br.senai.sp.cotia.tictactoeapp.databinding.FragmentJogoBinding;


public class jogoFragment extends Fragment {

    // variavel para acessar os elementos da view
    private FragmentJogoBinding binding;
    //vetor de botões
    private Button[] botoes;
    //matris de string do tabuleiro
    private String[][] tabuleiro;
    //variaveis para simbolos
    private String simbJog1, simbJog2, simbolo;
    private Random random;
    // variavel para numero de jogadas
    private int numJog= 0;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // chama binding

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
        simbJog1="X";
        simbJog2="O";


        random = new Random();

        sorteia();
        atualizaVez();

        //retorna root da binding
        return binding.getRoot();
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
            limpar();
        } else if (numJog == 9 && venceu() != true){
            Toast.makeText(getContext(), R.string.velha, Toast.LENGTH_LONG).show();
            limpar();
        } else {
            simbolo = simbolo.equals(simbJog1) ? simbJog2 : simbJog1;
            atualizaVez();
        }
    };
}