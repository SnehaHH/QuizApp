package com.example.myquizapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition = 0
    private var mUserName: String? = null

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var tvImage: ImageView? = null
    private var btnSubmit: Button? = null

    private var optionOne: TextView? = null
    private var optionTwo: TextView? = null
    private var optionThree: TextView? = null
    private var optionFour: TextView? = null

    private var mCorrectAnswers : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mQuestionsList = Constants.getQuestions()
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvImage = findViewById(R.id.ivImage)
        btnSubmit = findViewById(R.id.btnSubmit)

        optionOne = findViewById(R.id.optionOne)
        optionTwo = findViewById(R.id.optionTwo)
        optionThree = findViewById(R.id.optionThree)
        optionFour = findViewById(R.id.optionFour)

        optionOne?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        setQuestion()

    }

    private fun setQuestion() {
        defaultOptionsView()
        val question = mQuestionsList!![mCurrentPosition - 1]
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.question
        tvImage?.setImageResource(question.img)
        optionOne?.text = question.option1
        optionTwo?.text = question.option2
        optionThree?.text = question.option3
        optionFour?.text = question.option4
        btnSubmit?.text = "Submit"
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        optionOne?.let {
            options.add(0, it)
        }
        optionTwo?.let {
            options.add(1, it)
        }
        optionThree?.let {
            options.add(2, it)
        }
        optionFour?.let {
            options.add(3, it)
        }
        for (option in options) {
            option.setTextColor(resources.getColor(R.color.options))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_border_bg)
        }
    }

    private fun selectedOptionView(view: TextView, selectedOptionNum: Int) {
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        view.setTextColor(resources.getColor(R.color.black))
        view.setTypeface(view.typeface, Typeface.BOLD)
        view.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.optionOne -> {
                optionOne?.let {
                    selectedOptionView(it, 1)
                }
            }

            R.id.optionTwo -> {
                optionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }

            R.id.optionThree -> {
                optionThree?.let {
                    selectedOptionView(it, 3)
                }
            }

            R.id.optionFour -> {
                optionFour?.let {
                    selectedOptionView(it, 4)
                }
            }

            R.id.btnSubmit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }

                        else -> {
                            val intent : Intent = Intent(this, ResultPage::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers.toString())
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList!!.size.toString())
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition)
                        answerView(mSelectedOptionPosition, R.drawable.wrong_border_bg)
                    else
                        mCorrectAnswers++

                    answerView(question.correctAnswer, R.drawable.correct_border_bg)
                    btnSubmit?.text =
                        if (mCurrentPosition == mQuestionsList!!.size) "Finish" else "Next"
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                optionOne?.background = ContextCompat.getDrawable(this, drawableView)
            }

            2 -> {
                optionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            }

            3 -> {
                optionThree?.background = ContextCompat.getDrawable(this, drawableView)
            }

            4 -> {
                optionFour?.background = ContextCompat.getDrawable(this, drawableView)
            }

        }
    }
}