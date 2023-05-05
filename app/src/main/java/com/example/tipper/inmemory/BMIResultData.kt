package com.example.tipper.inmemory

import com.example.tipper.BMIActivity.BMIResult
import java.util.*
//Autor Karol Kuchnio s21912
object BMIResultData {
    private val bmiResults: MutableList<BMIResult> = ArrayList()

    fun getBMIResults(): List<BMIResult> {
        return bmiResults
    }

    fun addBMIResult(bmi: Double) {
        val result = BMIResult(bmi, Date())
        bmiResults.add(result)
    }
}


