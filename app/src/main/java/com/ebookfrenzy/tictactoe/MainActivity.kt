package com.ebookfrenzy.tictactoe

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Declare variables for the TextView and Buttons
    private lateinit var playerTurnTextView: TextView
    private lateinit var buttons: Array<Button>
    private var currentPlayer = "X" //Initialize the current player to "X"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the variables for the TextView and Buttons
        playerTurnTextView = findViewById(R.id.currentPlayer)
        buttons = arrayOf(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9)
        )

        // Set click listeners for the Buttons
        for (button in buttons) {
            button.setOnClickListener { onButtonClick(button) }
        }
        // Set click listener for the New Game button
        findViewById<Button>(R.id.newGameButton).setOnClickListener { newGame() }
    }

    // Handle button clicks
    private fun onButtonClick(button: Button) {
        if (button.text.isEmpty()) { // Check if the button is empty
            button.text = currentPlayer // Set the button text to the current player
            if ( checkWin() ) { // Check if the current player has won
                playerTurnTextView.text = "Player $currentPlayer Wins!"
                disableButtons() // Disable the buttons if the game is over
            } else if ( checkTie() ) { // Check if the game is a tie
                playerTurnTextView.text = "It's a Tie!"
            }
            else { // If the game is not over, switch to the other player
                currentPlayer = if (currentPlayer == "X") "O" else "X"
                playerTurnTextView.text = "Player $currentPlayer's Turn"
            }
        }
    }

    // Handle the New Game button click
    private fun newGame() {
        for (button in buttons) {
            button.text = "" // Clear the text of each button
            button.isEnabled = true
        }
        currentPlayer = "X" // Reset the current player to "X"
        playerTurnTextView.text = "Player X's Turn" // Update the text of the TextView
    }

    // Check if the current player has won
    private fun checkWin(): Boolean {
        val winPatterns = arrayOf(
            arrayOf(0, 1, 2), arrayOf(3, 4, 5), arrayOf(6, 7, 8), // Rows
            arrayOf(0, 3, 6), arrayOf(1, 4, 7), arrayOf(2, 5, 8), // Columns
            arrayOf(0, 4, 8), arrayOf(2, 4, 6) // Diagonals
        )

        for (pattern in winPatterns) {
            if (buttons[pattern[0]].text == buttons[pattern[1]].text &&
                buttons[pattern[1]].text == buttons[pattern[2]].text &&
                buttons[pattern[0]].text.isNotEmpty()) {
                return true // Return true if there is a winner
            }
        }
        return false // Return false if there is no winner
    }
    // Check if there is a tie in the game
    private fun checkTie(): Boolean {
        for (button in buttons) {
            if (button.text.isEmpty()) { // Check if any button is empty
                return false // Return false if there is an empty button
            }
        }
        return true // Return true if all buttons are filled and there is no winner
    }

    // Disable all the buttons for when the game is one and this is activated
    private fun disableButtons() {
        for (button in buttons) {
            button.isEnabled = false
        }
    }
}
