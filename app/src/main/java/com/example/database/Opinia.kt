package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class Opinia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opinia)

        initListeners()

    }

    private fun initListeners() {
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        buttonSubmit.setOnClickListener(buttonSubmitListener)


    }

    private val buttonSubmitListener = View.OnClickListener {
        submitInfoAboutOpinonButton()
    }

    private fun submitInfoAboutOpinion(): String {
        val name = findViewById<EditText>(R.id.editTextName)
        val surname = findViewById<EditText>(R.id.editTextSurname)
        val opinion = findViewById<EditText>(R.id.opinionET)
        val result= "Imie :"+name.text.toString()+ " Nazwisko  : "+surname.text.toString()+ "\n SEND NEW OPINION \n "+ opinion.text.toString()

        return result

    }

    private fun submitInfoAboutOpinonButton() {
        val intent = Intent()
        intent.putExtra("RESULT", submitInfoAboutOpinion())
        setResult(RESULT_OK, intent)
        finish()
    }
}