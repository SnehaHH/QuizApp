package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)

        val tvName: TextView = findViewById(R.id.textName)
        val tvScore: TextView = findViewById(R.id.textScore)
        val finishBtn: Button = findViewById(R.id.btnFinish)


        val name = intent.getStringExtra(Constants.USER_NAME)
        val totalQues = intent.getStringExtra(Constants.TOTAL_QUESTIONS)
        val score = intent.getStringExtra(Constants.CORRECT_ANSWERS)
        tvName.text = "Congratulations $name"
        tvScore.text = "You have scored $score out of $totalQues"

        finishBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}