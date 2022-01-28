package com.example.pac

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_1.*

class Activity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        //Creamos la variable para la canción
        val mediaplayer = MediaPlayer.create(this,R.raw.song)


        /* Antigua función con 3 botones
        //Creamos el listener para el botón play
        buttonPlay.setOnClickListener {
            if(!mediaplayer.isPlaying){
                //reproducimos la canción
                mediaplayer.start()

            }

        }


        //Creamos el listener para el botón pause
        buttonPause.setOnClickListener {
            if(mediaplayer.isPlaying){
                //pausamos la canción
                mediaplayer.pause()

            }

        }

        //Creamos el listener para el botón stop
        buttonStop.setOnClickListener{
            if(mediaplayer.isPlaying){
                //paramos la canción con el botón stop, pero utilizamos
                //un pause porque si no el botón play dejaría de funcionar
                mediaplayer.pause()
                //hacemos que la canción vuelva al principio
                mediaplayer.seekTo(0)
            }else{
                //hacemos que la canción vuelva al principio
                mediaplayer.seekTo(0)
            }

        }

        */

        //Me he ayudado de este vídeo para los toast https://www.youtube.com/watch?v=sOpS0mMN-sg

        //Creamos el intent para darle funcionalidad al botón e ir a la activity 2
        buttonActivity2.setOnClickListener {
            startActivity(Intent(this, Activity2::class.java))
            Toast.makeText(applicationContext,"Te encuentras en Activity2", Toast.LENGTH_SHORT).show()

        }

        //Creamos el intent para darle funcionalidad al botón e ir a la activity 3
        buttonActivity3.setOnClickListener {
            startActivity(Intent(this, Activity3::class.java))
            Toast.makeText(applicationContext,"Te encuentras en Activity3", Toast.LENGTH_SHORT).show()

        }

        //Creamos el intent para darle funcionalidad al botón e ir a la activity 4
        buttonActivity4.setOnClickListener {
            startActivity(Intent(this, Activity4::class.java))
            Toast.makeText(applicationContext,"Te encuentras en Activity4", Toast.LENGTH_SHORT).show()

        }


        //Creamos el listener para el botón play
        buttonPlay.setOnClickListener {
            if(!mediaplayer.isPlaying){
                //reproducimos la canción
                mediaplayer.start()
                //cambiamos el botón al de pausa
                buttonPlay.setBackgroundResource(R.drawable.pausebutton)


            }else {
                //le damos una función pause al botón de pausa
                mediaplayer.pause()
                //cambiamos el icono al botón para que al parar la música salga el de play
                buttonPlay.setBackgroundResource(R.drawable.playbutton)
            }

        }

        //Creamos el listener para el botón stop
        buttonStop.setOnClickListener{
            if(mediaplayer.isPlaying){
                //paramos la canción con el botón stop, pero al utilizar un pause necesitamos que
                //vuelva al principio la canción, de ahí la función seekTo
                mediaplayer.pause()
                //hacemos que la canción vuelva al principio
                mediaplayer.seekTo(0)
                //cambiamos el icono al botón para que al parar la música salga el de play
                buttonPlay.setBackgroundResource(R.drawable.playbutton)
            }else{
                //hacemos que la canción vuelva al principio
                mediaplayer.seekTo(0)
            }

        }

    }
}