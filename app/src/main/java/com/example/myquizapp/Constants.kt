package com.example.myquizapp

object Constants {

    const val USER_NAME :String = "user_name"
    const val TOTAL_QUESTIONS :String = "total_questions"
    const val  CORRECT_ANSWERS : String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionList = arrayListOf(
            Question(
                1,
                "What country does this flag belong to?",
                R.drawable.canada,
                "India",
                "Italy",
                "Canada",
                "Brazil",
                3
            ), Question(
                2,
                "What country does this flag belong to?",
                R.drawable.india,
                "India",
                "Italy",
                "Canada",
                "Brazil",
                1
            ), Question(
                3,
                "What country does this flag belong to?",
                R.drawable.peru,
                "United Kingdom",
                "Peru",
                "Canada",
                "Australia",
                2
            ), Question(
                4,
                "What country does this flag belong to?",
                R.drawable.brazil,
                "Argentina",
                "Peru",
                "Canada",
                "Brazil",
                4
            ), Question(
                5,
                "What country does this flag belong to?",
                R.drawable.newzealand,
                "India",
                "Australia",
                "New Zealand",
                "Brazil",
                3
            )
        )

        return questionList
    }
}