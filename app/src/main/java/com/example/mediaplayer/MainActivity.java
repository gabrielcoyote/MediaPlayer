package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekvolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mediaplayer serve para executar um arquivo de midia passando primeiro o conceito depois,
        // o nome da musica no exemplo e sininimos;

        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.sinonimos);
        InicializarSeekBar();


    }

    // ver se uma musica existe e caso sim executar ela

    public void ExecutarMusica (View view){
        if (mediaPlayer != null){
            mediaPlayer.start();
        }
    }

    // isPlaying retorna true se a musica esta executando e pause para a musica mas não destroi

   public void pausarMusica(View view){
        if( mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
   }
   // stop para a musica e destroi

   public void PararMusica(View view){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
   }
   private void InicializarSeekBar(){
       seekvolume = findViewById(R.id.seekVolume);

       //configurar o audio com "audio manager"

       audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

       // recuperar valor maximo e atual

       int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
       int volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

       //configurar os valores maximos para Seekbar

       seekvolume.setMax(volumeMaximo);
       seekvolume.setProgress(volumeAtual);

       // altera os dados da Seekbar aumentando o volume
       // os 3 paramestros configurados: primeiro tipo de Stream sendo configurado, progress e o
       // progresso da seekbar, flags consegue fazer configuaçoes individuais

       seekvolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

               audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });
   }
    // strop para a musica e destroi e release libera o espaço na memoria
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if( mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

   // metodo para parar a musica fora do app

   /* protected void onStop() {
        super.onStop();
       if (mediaPlayer.isPlaying()){
           mediaPlayer.stop();
       }
   } */
}