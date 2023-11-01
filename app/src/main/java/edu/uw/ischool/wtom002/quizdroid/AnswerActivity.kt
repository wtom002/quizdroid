package edu.uw.ischool.wtom002.quizdroid

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)

class AnswerActivity : AppCompatActivity() {

    private var currentQuestionIndex = 0

    private val quizQuestions = loadQuizQuestions(this, "quiz_questions.json")


    fun loadQuizQuestions(context: Context, jsonFileName: String): List<QuizQuestion> {
        val json: String
        val typeToken = object : TypeToken<List<QuizQuestion>>() {}.type

        try {
            val inputStream = context.assets.open(jsonFileName)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            json = bufferedReader.use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return emptyList()
        }

        return Gson().fromJson(json, typeToken)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_answer)

        val selectedAnswerTextView = findViewById<TextView>(R.id.userAnswerTextView)
        val correctAnswerTextView = findViewById<TextView>(R.id.correctAnswerTextView)
        val progressTextView = findViewById<TextView>(R.id.progressTextView)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val finishButton = findViewById<Button>(R.id.finishButton)

        val selectedAnswer = intent.getStringExtra("selected_answer")
        val correctAnswer = "Correct Answer" // Replace with the actual correct answer
        val isLastQuestion = false // Replace with logic to determine if it's the last question

        selectedAnswerTextView.text = "Your Answer: $selectedAnswer"
        correctAnswerTextView.text = "Correct Answer: $correctAnswer"

        // Update the progressTextView with correct vs. incorrect answers

        if (isLastQuestion) {
            nextButton.visibility = View.GONE
            finishButton.visibility = View.VISIBLE
        } else {
            nextButton.visibility = View.VISIBLE
            finishButton.visibility = View.GONE
        }

        nextButton.setOnClickListener {
            nextButton.setOnClickListener {
                if (currentQuestionIndex < quizQuestions.size - 1) {
                    // There are more questions
                    currentQuestionIndex++
                    updateUIWithQuestion(quizQuestions[currentQuestionIndex])
        }

        finishButton.setOnClickListener {
            // Handle navigation back to the topic list page
        }
    }
}
