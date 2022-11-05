package com.example.database.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.database.DAO.Student


class DataBaseHelper(
    context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    companion object {
        val TABLE_STUDENTS = "studentsData"
        val COLUMN_ID = "id"
        val COLUMN_STUDENT_NAME = "student_name"
        val COLUMN_STUDENT_SURNAME = "student_surname"
        val COLUMN_AGE = "student_age"
        val COLUMN_FIELD_OF_STUDY = "student_FieldOfStudy"
        val COLUMN_YEAR_OF_STUDY = "student_YearOfStudy"
        val COLUMN_AVERAGE_FROM_STUDIES = "student_AverageFromStudies"
        private const val DATABASE_NAME = "StudentsDataBase.db"
        private const val DATABASE_VERSION = 1;
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_STUDENTS_TABLE = ("CREATE TABLE " +
                TABLE_STUDENTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_STUDENT_NAME + " TEXT,"
                + COLUMN_STUDENT_SURNAME + " TEXT,"
                + COLUMN_AGE + " INTEGER,"
                + COLUMN_FIELD_OF_STUDY + " TEXT,"
                + COLUMN_YEAR_OF_STUDY + " INTEGER,"
                + COLUMN_AVERAGE_FROM_STUDIES + " DOUBLE" + ")")
        db.execSQL(CREATE_STUDENTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS)
        onCreate(db)
    }


    fun addStudent(student: Student) {
        val data = ContentValues()
        data.put(COLUMN_STUDENT_NAME, student.name)
        data.put(COLUMN_STUDENT_SURNAME, student.surname)
        data.put(COLUMN_AGE, student.age)
        data.put(COLUMN_FIELD_OF_STUDY, student.FieldOfStudy)
        data.put(COLUMN_YEAR_OF_STUDY, student.YearOfStudy)
        data.put(COLUMN_AVERAGE_FROM_STUDIES, student.AverageFromStudies)

        val db = this.writableDatabase
        db.insert(TABLE_STUDENTS, null, data)
        db.close()
    }


    fun findStudent(studentName: String, studentSurname: String): Student? {
        val query =
            "SELECT * FROM $TABLE_STUDENTS WHERE $COLUMN_STUDENT_NAME = \"$studentName\" " +
                    "and $COLUMN_STUDENT_SURNAME = \"$studentSurname\""
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        var student: Student? = null

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            val id = Integer.parseInt(cursor.getString(0))
            val studentName = cursor.getString(1)
            val studentSurname = cursor.getString(2)
            val studentAge = Integer.parseInt(cursor.getString(3))
            val studentFieldOfStudy = cursor.getString(4)
            val studentYearOfStudy = Integer.parseInt(cursor.getString(5))
            val studentAverageFromStudies = cursor.getString(6).toDouble()
            student = Student(
                id,
                studentName,
                studentSurname,
                studentAge,
                studentFieldOfStudy,
                studentYearOfStudy,
                studentAverageFromStudies
            )
            cursor.close()
        }
        db.close()
        return student
    }


    fun deleteStudent(studentName: String, studentSurname: String): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_STUDENTS WHERe $COLUMN_STUDENT_NAME = \"$studentName\" " +
                    "and $COLUMN_STUDENT_SURNAME = \"$studentSurname\""

        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(TABLE_STUDENTS, "$COLUMN_ID = ?", arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result
    }

    fun getAllStudents(): String {
        var allStudents: String = "";
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_STUDENTS"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val id = Integer.parseInt(cursor.getString(0))
                    val studentName = cursor.getString(1)
                    val studentSurname = cursor.getString(2)
                    val studentAge = Integer.parseInt(cursor.getString(3))
                    val studentFieldOfStudy = cursor.getString(4)
                    val studentYearOfStudy = Integer.parseInt(cursor.getString(5))
                    val studentAverageFromStudies = cursor.getString(6).toDouble()
                    val sign = "|"
                    allStudents =
                        "$allStudents\n$id \\ $studentName \\$studentSurname\\ $studentAge \\$studentFieldOfStudy\\ $studentYearOfStudy\\ $studentAverageFromStudies"
                } while (cursor.moveToNext())
            } else return "There is no student"
        }
        cursor.close()
        db.close()
        return allStudents
    }

    fun updateById(
        id: Int,
        name: String,
        surname: String,
        age: Int,
        fieldOfStudy: String,
        yearOfStudy: Int,
        averageFromStudies: Double
    ): Int {
        val data = ContentValues()
        data.put(COLUMN_STUDENT_NAME, name)
        data.put(COLUMN_STUDENT_SURNAME, surname)
        data.put(COLUMN_AGE, age)
        data.put(COLUMN_FIELD_OF_STUDY, fieldOfStudy)
        data.put(COLUMN_YEAR_OF_STUDY, yearOfStudy)
        data.put(COLUMN_AVERAGE_FROM_STUDIES, averageFromStudies)
        val whereclause = "$COLUMN_ID=?"
        val whereargs = arrayOf(id.toString())
        return this.writableDatabase.update(TABLE_STUDENTS, data, whereclause, whereargs)
    }

    //    fun deleteAllRecords(){
//        val query="DROP TABLE IF EXISTS $TABLE_STUDENTS"
//        val db = this.writableDatabase
//        db.execSQL(query)
//
//    }
    fun getAverageOfGrades(): String {
        var averageOfGradesDouble = 0.0
        var averageOfGradesString: String = "";
        var countOfRows = 0
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_STUDENTS"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    countOfRows += 1
                    val studentAverageFromStudies = cursor.getString(6).toDouble()

                    averageOfGradesDouble += studentAverageFromStudies
                } while (cursor.moveToNext())
            } else return "There is no student"
        }
        averageOfGradesDouble /= countOfRows
        averageOfGradesString = "Average of students grades=$averageOfGradesDouble"
        cursor.close()
        db.close()
        return averageOfGradesString

    }

    fun getData(query: String): Cursor? {
        val db = readableDatabase
        return db.rawQuery(query, null)

    }


}