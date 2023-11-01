package edu.uw.ischool.wtom002.quizdroid


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class AnswerActivity : AppCompatActivity() {

    private lateinit var answerGivenTextView: TextView
    private lateinit var correctAnswerTextView: TextView
    private lateinit var correctnessTextView: TextView
    private lateinit var nextButton: Button
    private lateinit var finishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val selectedAnswerIndex = intent.getIntExtra("selectedAnswerIndex", -1)
        val correctAnswerIndex = intent.getIntExtra("correctAnswerIndex", -1)
        val totalCorrect = intent.getIntExtra("totalCorrect", 0)
        val totalQuestions = intent.getIntExtra("totalQuestions", 0)

        answerGivenTextView.text = "Your answer: ${selectedAnswerIndex + 1}"
        correctAnswerTextView.text = "Correct answer: ${correctAnswerIndex + 1}"
        correctnessTextView.text = "You have $totalCorrect out of $totalQuestions correct"

        if (totalCorrect < totalQuestions) {
            // If there are more questions remaining, show the "Next" button.
            nextButton.setOnClickListener {
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("totalCorrect", totalCorrect)
                intent.putExtra("totalQuestions", totalQuestions)
                startActivity(intent)
                finish()
            }

            finishButton.visibility = View.GONE
        } else {
            // If all questions are answered, show the "Finish" button.
            nextButton.visibility = View.GONE
            finishButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
