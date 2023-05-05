package com.example.tipper

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tipper.inmemory.BMIResultData
import java.io.Serializable
import java.text.DecimalFormat
import java.util.*
//Autor Karol Kuchnio s21912
class BMIActivity : AppCompatActivity() {
    var bmiResultData: BMIResultData? = null

    var saveButton // save button
            : Button? = null
    var chartView // save button
            : Button? = null

    // currency and percent formatter objects
    // formatter object for number formatting
    val decimalFormat = DecimalFormat("#.##")

    var weight = 0.0 // weight entered by the user

    var height = 0.0 // height entered by the user

    var weightTextView // shows formatted bill amount
            : TextView? = null
    var heightTextView // shows tip percentage
            : TextView? = null

    var totalTextView // shows calculated total bill amount
            : TextView? = null

    // called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // call superclass onCreate
        setContentView(R.layout.activity_main) // inflate the GUI
        bmiResultData = BMIResultData;
        // get references to programmatically manipulated TextViews
        weightTextView = findViewById<View>(R.id.weightTextView) as TextView?
        weightTextView!!.isFocusable = true
        weightTextView!!.isFocusableInTouchMode = true
        heightTextView = findViewById<View>(R.id.heightTextView) as TextView?
        heightTextView!!.isFocusable = true
        heightTextView!!.isFocusableInTouchMode = true
        totalTextView = findViewById<View>(R.id.totalTextView) as TextView?

        // get references to programmatically manipulated TextViews
        totalTextView = findViewById<TextView>(R.id.totalTextView)
        totalTextView!!.text = decimalFormat.format(0.0)
        // set amountEditText's TextWatcher
        val weightEditText = findViewById<View>(R.id.weightEditText) as EditText
        weightEditText.addTextChangedListener(weightEditTextWatcher)

        // set heightEditText's TextWatcher
        val heightEditText = findViewById<View>(R.id.heightEditText) as EditText
        heightEditText.addTextChangedListener(heightEditTextWatcher)
        // hide the splash screen after a delay
        saveButton = findViewById<Button>(R.id.saveButton)
        saveButton!!.setOnClickListener { v: View? -> saveBMIResult() }
        chartView = findViewById<Button>(R.id.graph)
        chartView!!.setOnClickListener { v: View? -> startBMIGraphActivity() }
    }

    open fun startBMIGraphActivity() {
        val intent = Intent(this, BMIGraphActivity::class.java)
        startActivity(intent)
    }

    // Save the current BMI result to the list
    open fun saveBMIResult() {
        val bmi = totalTextView!!.text.toString().toDouble()
        bmiResultData!!.addBMIResult(bmi)
    }

    //calculate bmi
    open fun calculateBMI() {
        val bmi = weight / (height * height / 10000)

        // display BMI formatted to two decimal places
        totalTextView!!.text = decimalFormat.format(bmi)
    }


    // listener object for the EditText's text-changed events
    val weightEditTextWatcher: TextWatcher = object : TextWatcher {
        // called when the user modifies the bill amount
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            try { // get weight and display formatted value
                weight = s.toString().toDouble()
                weightTextView!!.text = decimalFormat.format(weight)
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                weight = 0.0
            }
            calculateBMI() // update the BMI TextView
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
        }
    }
    // listener object for the EditText's text-changed events
    val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        // called when the user modifies the height
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            try { // get height and display formatted value
                height = s.toString().toDouble()
                heightTextView!!.text = decimalFormat.format(height)
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                height = 0.0
            }
            calculateBMI() // update the BMI TextView
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
        }
    }

    class BMIResult(var bmi: Double, var date: Date) : Serializable


}
