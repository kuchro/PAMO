package com.example.tipper

import android.app.AlertDialog
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tipper.databinding.ActivityQuizBinding
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.nio.charset.StandardCharsets
//Autor Karol Kuchnio s21912
class QuizActivity : AppCompatActivity() {

    private var binding: ActivityQuizBinding? = null
    private var quizData: JSONArray? = null
    private var currentQuestionIndex = 0
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())


        // Load JSON data
        loadQuizData()

        // Display first question
        displayQuestion(currentQuestionIndex)

        // Set a click listener for the "Check Answer" button
        binding!!.contentScrolling.submitAnswerButton?.setOnClickListener { view -> checkAnswerAndProceed() }
    }

    private fun loadQuizData() {
        try { //Loading data from json file
            val inputStream = assets.open("quiz.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonData = String(buffer, StandardCharsets.UTF_8)
            quizData = JSONArray(jsonData)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        //or can be loaded as a jsonString
//        String jsonData = "[{\"id\":1,\"question\":\"question bla bla\",\"answers\":{\"a\":\"dsadas\",\"b\":\"sdsadas\",\"c\":\"dsadasd\"},\"validAnswer\":\"b\"}]";
//        try {
//            quizData = new JSONArray(jsonData);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    private fun displayQuestion(questionIndex: Int) {
        try {
            val questionData = quizData!!.getJSONObject(questionIndex)
            binding?.contentScrolling?.questionTextView?.setText(questionData.getString("question"))
            val answersData = questionData.getJSONObject("answers")
            binding?.contentScrolling?.answerA?.setText(answersData.getString("a"))
            binding?.contentScrolling?.answerB?.setText(answersData.getString("b"))
            binding?.contentScrolling?.answerC?.setText(answersData.getString("c"))
            binding?.contentScrolling?.answerD?.setText(answersData.getString("d"))
            binding?.contentScrolling?.answerRadioGroup?.clearCheck()

            // Update question number and button text
            binding?.contentScrolling?.questionNumberTextView?.setText((questionIndex + 1).toString() + "/" + quizData!!.length())
            if (questionIndex == quizData!!.length() - 1) {
                binding?.contentScrolling?.submitAnswerButton?.setText("Sprawdź wynik")
            } else {
                binding?.contentScrolling?.submitAnswerButton?.setText("Następne pytanie")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun checkAnswerAndProceed() {
        try {
            val selectedAnswerId: Int? = binding?.contentScrolling?.answerRadioGroup?.getCheckedRadioButtonId()
            if (selectedAnswerId == -1) {
                // No answer selected
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                return
            }
            val selectedAnswer = selectedAnswerId?.let { findViewById<RadioButton>(it) }
            val selectedAnswerText = selectedAnswer?.text.toString()
            val questionData = quizData!!.getJSONObject(currentQuestionIndex)
            val validAnswer = questionData.getString("validAnswer")
            if (selectedAnswerText.equals(validAnswer, ignoreCase = true)) {
                correctAnswers++
            }
            currentQuestionIndex++
            if (currentQuestionIndex < quizData!!.length()) {
                displayQuestion(currentQuestionIndex)
            } else {
                showQuizResults()
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun showQuizResults() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Quiz Results")
        builder.setMessage("Your score: " + correctAnswers + "/" + quizData!!.length())
        builder.setPositiveButton("Try Again") { dialog, which ->
            currentQuestionIndex = 0
            correctAnswers = 0
            displayQuestion(currentQuestionIndex)
        }
        builder.setNegativeButton("Close") { dialog, which -> finish() }
        val alertDialog = builder.create()
        alertDialog.show()
    }
}