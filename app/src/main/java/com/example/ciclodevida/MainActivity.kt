package com.example.ciclodevida

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private var mediaPlayer : MediaPlayer? = null
    private var posicion : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("Ciclo de vida", "onCreate")

        //Paso 1
        //mediaPlayer = MediaPlayer.create(this, R.raw.electronicaandroid) //Paso 6
       // mediaPlayer?.start() //Paso 3

    }//FinFunOnCreate

    override fun onStart(){
        super.onStart()
        Log.i("Ciclo de vida", "onStart")

        //Paso 3 Lo ideal NO es que la app se ejecute en onCreate, sino en onStart
        //mediaPlayer?.start()
        mediaPlayer = MediaPlayer.create(this, R.raw.electronicaandroid) //Paso 6

    }//FinFunOnStart

    override fun onResume(){
        super.onResume()
        Log.i("Ciclo de vida", "onResume")

        //Guardar la posición o segundo en el que se quedó reproduciendo la canción hasta antes de que se le diera pausa
        mediaPlayer?.seekTo(posicion)

        //Aquí se ocupa la memoria de la app
        //Conviene tener el start en onResume
        mediaPlayer?.start() //Inicia la canción en la posición que se indicó en la línea anterior, si no tuviera la línea anterior, empezaría desde el inicio.
        //Nunca se destruyó el recurso porque pasó de onPause a onResume, no es necesario volver a crearlo

    }//FinFunOnResume

    override fun onPause(){
        super.onPause()
        Log.i("Ciclo de vida", "onPause")

        //Paso 2
        mediaPlayer?.pause()

        //Para pausar la canción cuando se pause la app

        //Paso 8 ¿En qué poisición se quedó cuando le puse pausa?
        if (mediaPlayer != null) posicion = mediaPlayer!!.currentPosition


    }//FinFunOnPause

    override fun onStop(){
        super.onStop()
        Log.i("Ciclo de vida", "onStop")

        /*Lo ideal es que cuando esté en stop se detenga el recurso, se libere y se deje en nulo*/
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null


    }//FinFunOnStop

    override fun onRestart() {
        super.onRestart()
        Log.i("Ciclo de vida", "onRestart")

    }//FinFunOnRestart

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Ciclo de vida", "onDestroy")

    }//FinFunOnDestroy

}