package com.example.pac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_3.*
import kotlinx.android.synthetic.main.activity_4.*

class Activity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)

        //Creamos el intent para darle funcionalidad al botón y volver a la activity 1
        buttonVA1_3.setOnClickListener {
            startActivity(Intent(this, Activity1::class.java))
            Toast.makeText(applicationContext,"Te encuentras en Activity1", Toast.LENGTH_SHORT).show()

        }

    }
}