package com.example.database

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.database.DAO.Student
import com.example.database.DataBase.DataBaseHelper


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Students database"
        initListeners()

    }

    private fun initListeners() {

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonFind = findViewById<Button>(R.id.buttonFind)
        val buttonDelete = findViewById<Button>(R.id.buttonDelete)
        val buttonShowAll = findViewById<Button>(R.id.buttonShowAll)
        val buttonModify = findViewById<Button>(R.id.buttonModify)
        val buttonClear = findViewById<Button>(R.id.buttonClear)
        val buttonDropTable = findViewById<Button>(R.id.buttonDeleteAll)
        val buttonAvgGrade = findViewById<Button>(R.id.buttonAvg)
        val buttonAutor=findViewById<Button>(R.id.buttonAutor)
        val buttonOpinioon= findViewById<Button>(R.id.buttonOpinion)

        buttonAdd.setOnClickListener(buttonAddListener)
        buttonFind.setOnClickListener(buttonFindListener)
        buttonDelete.setOnClickListener(buttonDeleteListener)
        buttonShowAll.setOnClickListener(buttonShowAllListener)
        buttonModify.setOnClickListener(buttonModifyListener)
        buttonClear.setOnClickListener(buttonClearListener)
        buttonDropTable.setOnClickListener(buttonDropTableListener)
        buttonAvgGrade.setOnClickListener(buttonAverageGradeListener)
        buttonAutor.setOnClickListener(buttonAutorListener)
        buttonOpinioon.setOnClickListener(buttonOpinionListener)
    }

    private val buttonAddListener = View.OnClickListener {
        addStudentMain()
    }

    private val buttonFindListener = View.OnClickListener {
        showStudent()
    }
    private val buttonDeleteListener = View.OnClickListener {
        deleteStudent()
    }

    private val buttonShowAllListener = View.OnClickListener {
        viewAll()
    }
    private val buttonModifyListener = View.OnClickListener {
        callModify()
    }
    private val buttonClearListener = View.OnClickListener {
        clearField()
    }
    private val buttonDropTableListener = View.OnClickListener {
        dropTable(this)
    }

    private val buttonAverageGradeListener = View.OnClickListener {
        averageGrade()
    }
    private val buttonAutorListener = View.OnClickListener {
        callAutor()
    }
    private val buttonOpinionListener = View.OnClickListener {
        callOpinion()
    }


    private fun addStudentMain() {
        val studentName = findViewById<EditText>(R.id.nameET)
        val studentSurname = findViewById<EditText>(R.id.surnameET)
        val studentAge = findViewById<EditText>(R.id.ageET)
        val studentFieldOfStudy = findViewById<EditText>(R.id.fieldOfStudyET)
        val studentYearOfStudy = findViewById<EditText>(R.id.idET)
        val studentAverageFromStudies = findViewById<EditText>(R.id.averageFromStudiesET)
        val resultOfQuery = findViewById<TextView>(R.id.resultTV)


        val dataBaseHandler = DataBaseHelper(this, null, null, 1)
        val student = Student(
            studentName.text.toString(),
            studentSurname.text.toString(),
            studentAge.text.toString().toInt(),
            studentFieldOfStudy.text.toString(),
            studentYearOfStudy.text.toString().toInt(),
            studentAverageFromStudies.text.toString().toDouble()
        )
        dataBaseHandler.addStudent(student)
        resultOfQuery.text = "Succesfully added student!"
        clearField()

    }

    private fun showStudent() {
        val studentName = findViewById<EditText>(R.id.nameET)
        val studentSurname = findViewById<EditText>(R.id.surnameET)
        val studentAge = findViewById<EditText>(R.id.ageET)
        val studentFieldOfStudy = findViewById<EditText>(R.id.fieldOfStudyET)
        val studentYearOfStudy = findViewById<EditText>(R.id.idET)
        val studentAverageFromStudies = findViewById<EditText>(R.id.averageFromStudiesET)
        val resultOfQuery = findViewById<TextView>(R.id.resultTV)

        val dataBaseHandler = DataBaseHelper(this, null, null, 1)
        val student =
            dataBaseHandler.findStudent(studentName.text.toString(), studentSurname.text.toString())

        if (student != null) {
            studentAge.setText(student.age.toString())
            studentFieldOfStudy.setText(student.FieldOfStudy)
            studentYearOfStudy.setText(student.YearOfStudy.toString())
            studentAverageFromStudies.setText(student.AverageFromStudies.toString())
            resultOfQuery.text = student.toString()


        } else {
            resultOfQuery.text = "There is no such student!"
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
    }

    private fun deleteStudent() {
        val studentName = findViewById<EditText>(R.id.nameET)
        val studentSurname = findViewById<EditText>(R.id.surnameET)
        val resultOfQuery = findViewById<TextView>(R.id.resultTV)
        val dataBaseHandler = DataBaseHelper(this, null, null, 1)
        val result = dataBaseHandler.deleteStudent(
            studentName.text.toString(),
            studentSurname.text.toString()
        )

        if (result) {
            resultOfQuery.text = "Successfully_deleted_that_student"
        } else {
            resultOfQuery.text = "There_is_no_such_student"
        }
    }

    private fun clearField() {
        val studentName = findViewById<EditText>(R.id.nameET)
        val studentSurname = findViewById<EditText>(R.id.surnameET)
        val studentAge = findViewById<EditText>(R.id.ageET)
        val studentFieldOfStudy = findViewById<EditText>(R.id.fieldOfStudyET)
        val studentYearOfStudy = findViewById<EditText>(R.id.idET)
        val studentAverageFromStudies = findViewById<EditText>(R.id.averageFromStudiesET)
        studentName.text.clear()
        studentSurname.text.clear()
        studentAge.text.clear()
        studentFieldOfStudy.text.clear()
        studentYearOfStudy.text.clear()
        studentAverageFromStudies.text.clear()

    }

    private fun viewAll() {
        val resultOfQuery = findViewById<TextView>(R.id.resultTV)
        val dataBaseHandler = DataBaseHelper(this, null, null, 1)
        val result = dataBaseHandler.getAllStudents()
        resultOfQuery.text = result

    }

    private fun callModify() {
        val modifyIntent = Intent(this, SecondActivity::class.java)
        modifyIntent.putExtra("result", "W TYM OKNIE MOŻESZ MODYFIKOWAC REKORDY NA PODSTAWIE ID")
        startActivityForResult(modifyIntent, 2)

    }
    private fun callOpinion() {
        val opinionIntent = Intent(this, Opinia::class.java)
//        opinionIntent.putExtra("result", "W TYM OKNIE MOŻESZ MODYFIKOWAC REKORDY NA PODSTAWIE ID")
        startActivityForResult(opinionIntent, 4)

    }

    private fun callAutor() {
        val autoIntent = Intent(this, Autor::class.java)
//        modifyIntent.putExtra("result", "W TYM OKNIE MOŻESZ MODYFIKOWAC REKORDY NA PODSTAWIE ID")
        startActivityForResult(autoIntent, 3)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                val resultOfQuery = findViewById<TextView>(R.id.resultTV)

                val result = data?.getStringExtra("RESULT")
                resultOfQuery.text = result

            }
        }
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                val resultOfQuery = findViewById<TextView>(R.id.resultTV)

                val result = data?.getStringExtra("RESULT")
                resultOfQuery.text = result

            }
        }
        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                val resultOfQuery = findViewById<TextView>(R.id.resultTV)

                val result = data?.getStringExtra("RESULT")
                resultOfQuery.text = result

            }
        }
    }




    private fun dropTable(context: Context) {
        context.toast("TEST TOAST")

    }

    fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun averageGrade() {
        val resultOfQuery = findViewById<TextView>(R.id.resultTV)
        val dataBaseHandler = DataBaseHelper(this, null, null, 1)
        val result = dataBaseHandler.getAverageOfGrades()
        resultOfQuery.text = result
    }

}