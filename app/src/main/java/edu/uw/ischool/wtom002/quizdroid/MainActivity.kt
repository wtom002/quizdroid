package edu.uw.ischool.wtom002.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            val selectedRadioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            if (selectedRadioButton != null) {
                val selectedAnswer = selectedRadioButton.text.toString()
                val intent = Intent(this, AnswerActivity::class.java)
                intent.putExtra("selected_answer", selectedAnswer)
                startActivity(intent)
            }
        }
    }
}
