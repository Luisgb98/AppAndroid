package com.example.pac

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_1.*
import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        //Creamos el intent para darle funcionalidad al botón y volver a la activity 1
        buttonVA1.setOnClickListener {
            startActivity(Intent(this, Activity1::class.java))
            Toast.makeText(applicationContext,"Te encuentras en Activity1", Toast.LENGTH_SHORT).show()

        }

        //Avisa de la creación de la tabla en la base de datos
        buttonCT.setOnClickListener {
            Toast.makeText(applicationContext,"Has creado una nueva tabla en la base de datos", Toast.LENGTH_SHORT).show()

        }

        //Creamos el intent para darle funcionalidad al botón y ir al Activity para ingresar datos
        buttonID.setOnClickListener {
            startActivity(Intent(this, ActivityInsDatos::class.java))
            Toast.makeText(applicationContext,"Ingresa todos los datos", Toast.LENGTH_SHORT).show()

        }

        //Creamos el intent para darle funcionalidad al botón y ir al Activity para consultar datos
        buttonCD.setOnClickListener {
            startActivity(Intent(this, ActivityConDatos::class.java))
            Toast.makeText(applicationContext,"Consultando datos...", Toast.LENGTH_SHORT).show()

        }

    }
}