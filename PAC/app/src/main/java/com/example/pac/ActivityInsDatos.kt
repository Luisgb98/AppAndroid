package com.example.pac

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pac.databinding.ActivityInsDatosBinding
import kotlinx.android.synthetic.main.activity_2.*
import kotlinx.android.synthetic.main.activity_ins_datos.*

class ActivityInsDatos : AppCompatActivity() {

    lateinit var binding: ActivityInsDatosBinding
    lateinit var baseDBHelper: SQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ins_datos)

        binding = ActivityInsDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        baseDBHelper = SQLite(this)

        binding.buttonAdd.setOnClickListener{
            if (binding.edDato1.text.isNotBlank() && binding.edDato2.text.isNotBlank()) {
                baseDBHelper.addDato(binding.edDato1.text.toString(), binding.edDato2.text.toString())
                binding.edDato1.text.clear()
                binding.edDato2.text.clear()
                Toast.makeText(this, "Se han guardado los datos", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this, "No se han podido guardar los datos", Toast.LENGTH_LONG).show()
            }

        }

        binding.buttonVer.setOnClickListener{
            binding.textVer.text = ""
            val db : SQLiteDatabase = baseDBHelper.readableDatabase
            val cursor = db.rawQuery("SELECT * FROM tabla", null)

            if (cursor.moveToFirst()) {
                do {
                    binding.textVer.append(cursor.getInt(0).toString() + ": ")
                    binding.textVer.append(cursor.getString(1).toString() + ", ")
                    binding.textVer.append(cursor.getString(2).toString() + "\n")

                }while (cursor.moveToNext())
            }

        }

        binding.buttonEliminar.setOnClickListener {
            var cantidad = 0

            if (binding.edID.text.isNotBlank()) {
                cantidad = baseDBHelper.borrarDato(binding.edID.text.toString().toInt())
                binding.edID.text.clear()
                Toast.makeText(this, "Datos borrados: $cantidad", Toast.LENGTH_SHORT).show()

            }

        }

        binding.buttonModificar.setOnClickListener {
            if (binding.edDato1.text.isNotBlank() && binding.edDato2.text.isNotBlank() && binding.edID.text.isNotBlank()) {
                baseDBHelper.modificarDato(binding.edID.text.toString().toInt(), binding.edDato1.text.toString(), binding.edDato2.text.toString())
                binding.edID.text.clear()
                binding.edDato1.text.clear()
                binding.edDato2.text.clear()
                Toast.makeText(this, "Se han modificado los datos", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this, "Asegurate de rellenar todos los campos", Toast.LENGTH_LONG).show()
            }

        }

        buttonVA.setOnClickListener {
            startActivity(Intent(this, Activity2::class.java))
            Toast.makeText(applicationContext,"Has vuelto al Activity2", Toast.LENGTH_SHORT).show()

        }

    }

}