package com.example.wordle

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        var guessOne = findViewById<TextView>(R.id.guessOne)
        var checkOne = findViewById<TextView>(R.id.checkOne)
        var guessTwo = findViewById<TextView>(R.id.guessTwo)
        var checkTwo = findViewById<TextView>(R.id.checkTwo)
        var guessThree = findViewById<TextView>(R.id.guessThree)
        var checkThree = findViewById<TextView>(R.id.checkThree)
        val ansView = findViewById<TextView>(R.id.answer)
        val guessInput = findViewById<Button>(R.id.guessBtn)
        val editText = findViewById<EditText>(R.id.userInput)

        var guesses = 3

        guessInput.setOnClickListener {
            // reset game
            if (guesses == 0){
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)
            }

            // guesses remaining conditional
            if (guesses != 0) {
                var correctGuess = false
                val editTextString = editText.text.toString().uppercase(Locale.getDefault())   // to string for edit text
                if (editTextString.length == 4) {
                    ansView.text = ""
                    val result = checkGuess(editTextString, wordToGuess)
                    checkGuess(editTextString, wordToGuess)

                    // guessed right
                    if (result == "OOOO") {
                        ansView.text = "Correct! Answer: $wordToGuess"
                        guessInput.text = "Reset"
                        correctGuess = true
                        guesses = 0
                    } else {
                        guesses--

                        if (guesses == 0) {
                            ansView.text = "Answer: $wordToGuess"
                            guessInput.text = "Reset"
                        }
                    }
                }

                else
                    ansView.text = "Enter a Four Letter Word Only"
            }
        }
    }

    /**
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}