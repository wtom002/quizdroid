package edu.uw.ischool.wtom002.quizdroid


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
class QuestionActivity : AppCompatActivity() {
    private val questions = mutableListOf(
        Question("What is the capital of France?", listOf("Paris", "Berlin", "Madrid", "London"), 0),
        Question("Which planet is known as the Red Planet?", listOf("Mars", "Venus", "Jupiter", "Saturn"), 0),
    )

    private var currentQuestionIndex = 0
    private var totalCorrect = 0

    private lateinit var questionTextView: TextView
    private lateinit var radioButton1: RadioButton
    private lateinit var radioButton2: RadioButton
    private lateinit var radioButton3: RadioButton
    private lateinit var radioButton4: RadioButton
    private lateinit var radioGroup: RadioGroup
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        updateUI()

        submitButton.setOnClickListener {
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            if (selectedRadioButtonId != -1) {
                val selectedAnswerIndex = when (selectedRadioButtonId) {
                    R.id.radioButton1 -> 0
                    R.id.radioButton2 -> 1
                    R.id.radioButton3 -> 2
                    R.id.radioButton4 -> 3
                    else -> -1
                }

                val correctAnswerIndex = questions[currentQuestionIndex].correctAnswerIndex

                if (selectedAnswerIndex == correctAnswerIndex) {
                    totalCorrect++
                }

                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                    updateUI()
                    radioGroup.clearCheck()
                } else {
                    val intent = Intent(this, AnswerActivity::class.java)
                    intent.putExtra("totalCorrect", totalCorrect)
                    intent.putExtra("totalQuestions", questions.size)
                    startActivity(intent)
                }
            }
        }
    }

    private fun updateUI() {
        val currentQuestion = questions[currentQuestionIndex]
        questionTextView.text = currentQuestion.questionText
        radioButton1.text = currentQuestion.options[0]
        radioButton2.text = currentQuestion.options[1]
        radioButton3.text = currentQuestion.options[2]
        radioButton4.text = currentQuestion.options[3]
    }
}



