package br.senai.sp.cotia.tictactoeapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.prefs.Preferences;

public class PrefsUtil {

    public static String getSimboloJog1(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return  preferences.getString("simb_jog1","X");

    }

    public static String getSimboloJog2(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return  preferences.getString("simb_jog2","O");

    }

    public static void setSimboloJog1(String simbolo, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("simb_Jog1", simbolo);
        editor.commit();

    }

    public static void setSimboloJog2(String simbolo, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("simb_Jog2", simbolo);
        editor.commit();

    }

    //usar para pegar nome e guardar nos preferences

    public static String getNomeJog1(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return  preferences.getString("nome1","jogador1");

    }

    public static String getNomeJog2(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return  preferences.getString("nome2","jogador2");

    }

    public static void setNomeJog1(String nomeJog1, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nome1", nomeJog1);
        editor.commit();

    }

    public static void setNomeJog2(String nomeJog2, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nome2", nomeJog2);
        editor.commit();

    }





}
