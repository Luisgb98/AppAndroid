package com.example.pac

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_3.*

class Activity3 : AppCompatActivity() {

    private val REQUEST_CAMERA = 1002

    var foto: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        abreCamara_Click()

        //Creamos el intent para darle funcionalidad al botón y volver a la activity 1
        buttonVA1_2.setOnClickListener {
            startActivity(Intent(this, Activity1::class.java))
            Toast.makeText(applicationContext,"Te encuentras en Activity1", Toast.LENGTH_SHORT).show()

        }

    }

    //Detectamos cuando se pulsa el botón de la cámara
    private fun abreCamara_Click(){
        imageButtonCamara.setOnClickListener(){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                    || checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    //Pedimos permiso al usuario
                    val permisosCamara = arrayOf(android.Manifest.permission.CAMERA,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permisosCamara,REQUEST_CAMERA)
                }else
                    abreCamara()
            }else{
                abreCamara()

            }

        }

    }

    //Comprobamos si el usuario ha dado permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CAMERA -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    abreCamara()
                else
                    Toast.makeText(applicationContext, "No puedes acceder a la cámara",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Cámara hecha con el nuevo ActivityResult

    val getAction = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            imageGuardar.setImageURI(foto)
    }

    //Abre la cámara
    private fun abreCamara(){
        val value = ContentValues()
        value.put(MediaStore.Images.Media.TITLE, "Nueva imagen")
        foto = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,value)
        val camaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT,foto)
        getAction.launch(camaraIntent)

    }

    //https://www.youtube.com/watch?v=oyIsOhb9ig4 Me he ayudado de este vídeo para la cámara


    //Cámara hecha con el onActivityForResult que esta deprecated, para cambiarlo he seguido este vídeo https://www.youtube.com/watch?v=5GMwP9ppjdk

    /*

    //Abre la cámara
    private fun abreCamara(){
        val value = ContentValues()
        value.put(MediaStore.Images.Media.TITLE, "Nueva imagen")
        foto = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,value)
        val camaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT,foto)
        startActivityForResult(camaraIntent, REQUEST_CAMERA)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CAMERA) {
            imageGuardar.setImageURI(foto)
        }
    }


     */

}