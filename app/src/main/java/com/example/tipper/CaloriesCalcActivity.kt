package com.example.tipper

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
//Autor Karol Kuchnio s21912
class CaloriesCalcActivity : AppCompatActivity() {
    private val decimalFormat = DecimalFormat("#.##")
    private var ageEditText: EditText? = null
    private var weightEditText: EditText? = null
    private var heightEditText: EditText? = null
    private var genderRadioGroup: RadioGroup? = null
    private var maleRadioButton: RadioButton? = null
    private var femaleRadioButton: RadioButton? = null
    private lateinit var calculateButton: Button;
    private var resultTextView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories_calc)
        ageEditText = findViewById(R.id.age)
        weightEditText = findViewById(R.id.weight)
        heightEditText = findViewById(R.id.height)
        genderRadioGroup = findViewById(R.id.gender)
        maleRadioButton = findViewById(R.id.male)
        femaleRadioButton = findViewById(R.id.female)
        calculateButton = findViewById(R.id.calculate)
        resultTextView = findViewById(R.id.calories_result)
        val radioGroup = findViewById<RadioGroup>(R.id.gender)
        radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            val radioButton = radioGroup.findViewById<View>(checkedId) as RadioButton
            if (radioButton != null) {
                // hide the keyboard
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(radioButton.windowToken, 0)
            }
        }
        calculateButton.setOnClickListener(View.OnClickListener { calculateCalories() })
    }

    @SuppressLint("StringFormatInvalid")
    private fun calculateCalories() {
        // get below input values
        val age = ageEditText!!.text.toString().toInt()
        val weight = weightEditText!!.text.toString().toFloat()
        val height = heightEditText!!.text.toString().toInt()
        val isMale = maleRadioButton!!.isChecked

        // here calculate BMR
        val bmr: Double
        bmr = if (isMale) {
            88.362 + 13.397 * weight + 4.799 * height - 5.677 * age
        } else {
            447.593 + 9.247 * weight + 3.098 * height - 4.330 * age
        }
        resultTextView!!.text = decimalFormat.format(bmr)
    }
}