package com.example.pac

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.Toast
import com.example.pac.databinding.ActivityConDatosBinding
import com.example.pac.databinding.ItemListviewBinding
import kotlinx.android.synthetic.main.activity_con_datos.*
import kotlinx.android.synthetic.main.activity_ins_datos.*

class ActivityConDatos : AppCompatActivity() {

    lateinit var binding: ActivityConDatosBinding
    lateinit var baseDBHelper: SQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        baseDBHelper = SQLite(this)

        val db : SQLiteDatabase = baseDBHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM tabla", null)

        val adaptador = CursorAdapterListView(this, cursor)
        binding.lvDatos.adapter = adaptador
        db.close()

        buttonVAtras.setOnClickListener {
            startActivity(Intent(this, Activity2::class.java))
            Toast.makeText(applicationContext,"Has vuelto al Activity2", Toast.LENGTH_SHORT).show()

        }

    }

    inner class CursorAdapterListView(context: Context, cursor: Cursor) : CursorAdapter(context, cursor, FLAG_REGISTER_CONTENT_OBSERVER) {

        override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(context)
            return inflater.inflate(R.layout.item_listview, parent, false)

        }

        override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
            val bindingItems = ItemListviewBinding.bind(view!!)
            bindingItems.textDato1.text = cursor!!.getString(1)
            bindingItems.textDato2.text = cursor!!.getString(2)

            view.setOnClickListener {
                Toast.makeText(this@ActivityConDatos, "${bindingItems.textDato1.text}, ${bindingItems.textDato2.text}",Toast.LENGTH_SHORT).show()

            }

        }
    }

}