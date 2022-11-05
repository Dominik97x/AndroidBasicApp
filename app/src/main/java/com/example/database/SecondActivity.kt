package com.example.database


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.database.DataBase.DataBaseHelper

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        title = "Modify student"
        initListeners()
        val resultOfQuery = findViewById<TextView>(R.id.resultTV)
        val setString = intent.getStringExtra("result").toString()
        resultOfQuery.text = setString


    }

    private fun initListeners() {
        val buttonModify = findViewById<Button>(R.id.buttonModify)

        buttonModify.setOnClickListener(buttonModifyListener)
    }

    private val buttonModifyListener = View.OnClickListener {
        returnData()
    }

    //    private fun modifyData(): String? {
//        val studentID = findViewById<EditText>(R.id.idET)
//        val studentName = findViewById<EditText>(R.id.nameET)
//        val studentSurname = findViewById<EditText>(R.id.surnameET)
//        val studentAge = findViewById<EditText>(R.id.ageET)
//        val studentFieldOfStudy = findViewById<EditText>(R.id.fieldOfStudyET)
//        val studentYearOfStudy = findViewById<EditText>(R.id.yearOfStudyET)
//        val studentAverageFromStudies = findViewById<EditText>(R.id.averageFromStudiesET)
//        val dataBaseHandler = DataBaseHelper(this, null, null, 1)
//        val query = "Select * from Students where id = $studentID"
//        var message= ""
//        if (dataBaseHandler.getData(query)!!.count == 1) {
//            dataBaseHandler.updateById(
//                studentID.text.toString().toInt(),
//                studentName.text.toString(),
//                studentSurname.text.toString(),
//                studentAge.text.toString().toInt(),
//                studentFieldOfStudy.text.toString(),
//                studentYearOfStudy.text.toString().toInt(),
//                studentAverageFromStudies.text.toString().toDouble()
//
//            )
//            message = "Successfully updated one of the record"
//        } else
//        {
//            message= "There is no student with such ID"
//        }
//
//
//        return message
//    }
    private fun modifyData(): String? {
        val studentID = findViewById<EditText>(R.id.idET)
        val studentName = findViewById<EditText>(R.id.nameET)
        val studentSurname = findViewById<EditText>(R.id.surnameET)
        val studentAge = findViewById<EditText>(R.id.ageET)
        val studentFieldOfStudy = findViewById<EditText>(R.id.fieldOfStudyET)
        val studentYearOfStudy = findViewById<EditText>(R.id.yearOfStudyET)
        val studentAverageFromStudies = findViewById<EditText>(R.id.averageFromStudiesET)
        val dataBaseHandler = DataBaseHelper(this, null, null, 1)


        dataBaseHandler.updateById(
            studentID.text.toString().toInt(),
            studentName.text.toString(),
            studentSurname.text.toString(),
            studentAge.text.toString().toInt(),
            studentFieldOfStudy.text.toString(),
            studentYearOfStudy.text.toString().toInt(),
            studentAverageFromStudies.text.toString().toDouble()

        )

        return "Successfully updated one of the record"
    }

    private fun returnData() {
        val intent = Intent()
        intent.putExtra("RESULT", modifyData())
        setResult(RESULT_OK, intent)
        finish()
    }


}