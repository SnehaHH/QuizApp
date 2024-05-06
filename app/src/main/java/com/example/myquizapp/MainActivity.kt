package com.example.myquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name = findViewById<EditText>(R.id.inputName)
        findViewById<Button>(R.id.btnStart).setOnClickListener {
            if (name.text.length > 12) {
                Toast.makeText(this, "Name must have less than 12 characters", Toast.LENGTH_LONG)
                    .show()
            } else if (name.text.isNotEmpty()) {
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, name.text.toString())
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter your name!", Toast.LENGTH_LONG).show()
            }
        }
    }
}