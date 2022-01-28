package com.example.pac

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//He seguido este canal para guiarme a la hora de crear la base de datos https://www.youtube.com/channel/UCouf3yM2mVl9eWYj-K9Gh1A

class SQLite(context: Context) : SQLiteOpenHelper(context, "base.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE tabla " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dato1 TEXT, dato2 TEXT)"

        db!!.execSQL(ordenCreacion)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS tabla"

        db!!.execSQL(ordenBorrado)
        onCreate(db)

    }

    fun addDato(dato1: String, dato2: String) {
        val datos = ContentValues()
        datos.put("dato1", dato1)
        datos.put("dato2", dato2)

        val db = this.writableDatabase
        db.insert("tabla", null, datos)
        db.close()

    }

    fun borrarDato(id: Int) : Int {
        val args = arrayOf(id.toString())

        val db = this.writableDatabase
        val borrados = db.delete("tabla", "_id = ?", args)
        db.close()
        return borrados

    }

    fun modificarDato(id: Int, dato1: String, dato2: String) {
        val args = arrayOf(id.toString())

        val datos = ContentValues()
        datos.put("dato1", dato1)
        datos.put("dato2", dato2)

        val db = this.writableDatabase
        db.update("tabla", datos,"_id = ?", args)
        db.close()

    }

}