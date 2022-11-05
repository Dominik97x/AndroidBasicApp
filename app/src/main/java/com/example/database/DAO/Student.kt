package com.example.database.DAO

class Student {
    var id: Int = 0
    var name: String= " "
    var surname: String= " "
    var age: Int = 0
    var FieldOfStudy: String=" "
    var YearOfStudy: Int = 0
    var AverageFromStudies: Double = 0.0

    constructor(id: Int, name: String, surname: String, age: Int, FieldOfStudy: String, YearOfStudy: Int, AverageFromStudies: Double) {
        this.id = id
        this.name = name
        this.surname = surname
        this.age = age
        this.FieldOfStudy = FieldOfStudy
        this.YearOfStudy = YearOfStudy
        this.AverageFromStudies = AverageFromStudies
    }

    constructor(name: String, surname: String, age: Int, FieldOfStudy: String, YearOfStudy: Int, AverageFromStudies: Double) {
        this.name = name
        this.surname = surname
        this.age = age
        this.FieldOfStudy = FieldOfStudy
        this.YearOfStudy = YearOfStudy
        this.AverageFromStudies = AverageFromStudies
    }

    override fun toString(): String {
        return "Id=$id, Imie:$name, Nazwisko:$surname, Wiek:$age, Kierunek:$FieldOfStudy, Rok:$YearOfStudy, Srednia:$AverageFromStudies"
    }


}