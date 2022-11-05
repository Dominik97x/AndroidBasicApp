package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class Autor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autor)
        initListeners()

    }

    private fun initListeners() {
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        buttonSubmit.setOnClickListener(buttonSubmitListener)


    }

    private val buttonSubmitListener = View.OnClickListener {
        submitInfoAboutAutor()

    }

    private fun setInfoAboutAutor(): String {
        val name = findViewById<EditText>(R.id.editTextName)
        val surname = findViewById<EditText>(R.id.editTextSurname)
        //        val modifyIntent = Intent(this, SecondActivity::class.java)
//        modifyIntent.putExtra("result", result)
//        startActivityForResult(modifyIntent, 2)

        return "Autor\nImie:  "+name.text.toString()+ "   Nazwisko:  "+surname.text.toString()

    }
    private fun submitInfoAboutAutor() {
        val intent = Intent()
        intent.putExtra("RESULT", setInfoAboutAutor())
        setResult(RESULT_OK, intent)
        finish()
    }
}