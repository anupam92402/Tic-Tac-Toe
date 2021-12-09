package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startBtn.setOnClickListener {
            val playerOne = player_one.text.toString()
            val playerTwo = player_two.text.toString()
            if (playerOne.isEmpty()) {
                player_one.error = "Enter Your Name"
            }
            if (playerTwo.isEmpty()) {
                player_two.error = "Enter Your Name"
            }
            if (playerOne.isNotEmpty() && playerTwo.isNotEmpty()) {
                val intent = Intent(this, Game::class.java)
                intent.putExtra("player_one", playerOne)
                intent.putExtra("player_two", playerTwo)
                startActivity(intent)
            }
        }
    }


}