package com.ebookfrenzy.tictactoe

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var playerTurnTextView: TextView
    private lateinit var buttons: Array<Button>
    private var currentPlayer = "X"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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

        for (button in buttons) {
            button.setOnClickListener { onButtonClick(button) }
        }

        findViewById<Button>(R.id.newGameButton).setOnClickListener { newGame() }
    }

    private fun onButtonClick(button: Button) {
        if (button.text.isEmpty()) {
            button.text = currentPlayer
            currentPlayer = if (currentPlayer == "X") "O" else "X"
            playerTurnTextView.text = "Player $currentPlayer's Turn"
        }
    }

    private fun newGame() {
        for (button in buttons) {
            button.text = ""
        }
        currentPlayer = "X"
        playerTurnTextView.text = "Player X's Turn"
    }
}
