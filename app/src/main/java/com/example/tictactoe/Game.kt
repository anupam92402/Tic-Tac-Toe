package com.example.tictactoe

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class Game : AppCompatActivity() {
    private var turn: Int = 0
    private var count: Int = 0
    private var board = IntArray(9)
    private var stack = Stack<Int>()
    private var pressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val playerOne = intent.getStringExtra("player_one")
        val playerTwo = intent.getStringExtra("player_two")
        playerOneText.text = playerOne
        playerTwoText.text = playerTwo
        image1.setOnClickListener {
            setImage(0, image1)
        }
        image2.setOnClickListener {
            setImage(1, image2)
        }
        image3.setOnClickListener {
            setImage(2, image3)
        }
        image4.setOnClickListener {
            setImage(3, image4)
        }
        image5.setOnClickListener {
            setImage(4, image5)
        }
        image6.setOnClickListener {
            setImage(5, image6)
        }
        image7.setOnClickListener {
            setImage(6, image7)
        }
        image8.setOnClickListener {
            setImage(7, image8)
        }
        image9.setOnClickListener {
            setImage(8, image9)
        }
        undo_btn.setOnClickListener {
            if (stack.isEmpty()) {
                Toast.makeText(this, "Play a Move First", Toast.LENGTH_SHORT).show()
            } else {
                count--
                val pop = stack.pop()
                board[pop - 1] = 0
                if (turn == 1) {
                    ll_1.setBackgroundResource(R.drawable.turn)
                    ll_2.setBackgroundResource(R.drawable.custom_turn_player)
                    turn = 0
                } else {
                    ll_1.setBackgroundResource(R.drawable.custom_turn_player)
                    ll_2.setBackgroundResource(R.drawable.turn)
                    turn = 1
                }

                when (pop) {
                    1 -> image1.setImageResource(R.drawable.custom_player)
                    2 -> image2.setImageResource(R.drawable.custom_player)
                    3 -> image3.setImageResource(R.drawable.custom_player)
                    4 -> image4.setImageResource(R.drawable.custom_player)
                    5 -> image5.setImageResource(R.drawable.custom_player)
                    6 -> image6.setImageResource(R.drawable.custom_player)
                    7 -> image7.setImageResource(R.drawable.custom_player)
                    8 -> image8.setImageResource(R.drawable.custom_player)
                    9 -> image9.setImageResource(R.drawable.custom_player)
                }
            }
        }
    }

    override fun onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

    private fun setImage(player: Int, image: ImageView) {
        if (board[player] != 0) {
            return
        }
        count++
        stack.add(player + 1)
        if (turn == 0) {
            turn = 1
            board[player] = 1
            image.setImageResource(R.drawable.zero)
            ll_1.setBackgroundResource(R.drawable.custom_turn_player)
            ll_2.setBackgroundResource(R.drawable.turn)
        } else {
            turn = 0
            board[player] = 2
            image.setImageResource(R.drawable.cross)
            ll_2.setBackgroundResource(R.drawable.custom_turn_player)
            ll_1.setBackgroundResource(R.drawable.turn)
        }
        var result: Int = 0
        if (count >= 5) {
            result = checkWinner()
        }
        if (count == 9 && result == 0) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Draw")
            builder.setMessage("Do you want to Play Again?")
            builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                reset()
            }
            builder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
                finish()
            }
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        } else if (result != 0) {
            var winner: String = ""
            if (result == 1) {
                winner = playerOneText.text.toString()
            } else {
                winner = playerTwoText.text.toString()
            }
            val builder = AlertDialog.Builder(this)
            builder.setTitle("$winner won the Game")
            builder.setMessage("Do you want to Play Again?")
            builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                reset()
            }
            builder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
                finish()
            }
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

    }

    private fun reset() {
        turn = 0
        count = 0
        for (i in 0..8) {
            board[i] = 0
        }
        ll_2.setBackgroundResource(R.drawable.custom_turn_player)
        ll_1.setBackgroundResource(R.drawable.turn)
        image1.setImageResource(R.drawable.custom_player)
        image2.setImageResource(R.drawable.custom_player)
        image3.setImageResource(R.drawable.custom_player)
        image4.setImageResource(R.drawable.custom_player)
        image5.setImageResource(R.drawable.custom_player)
        image6.setImageResource(R.drawable.custom_player)
        image7.setImageResource(R.drawable.custom_player)
        image8.setImageResource(R.drawable.custom_player)
        image9.setImageResource(R.drawable.custom_player)
        stack.clear()
    }

    private fun checkWinner(): Int {
        //horizontally
        if (board[0] == board[1] && board[0] == board[2]) {
            if (board[0] == 1) {
                return 1
            } else if (board[0] == 2) {
                return 2
            }
        }

        if (board[3] == board[4] && board[3] == board[5]) {
            if (board[3] == 1) {
                return 1
            } else if (board[3] == 2) {
                return 2
            }
        }

        if (board[6] == board[7] && board[6] == board[8]) {
            if (board[6] == 1) {
                return 1
            } else if (board[6] == 2) {
                return 2
            }
        }

        //vertically
        if (board[0] == board[3] && board[0] == board[6]) {
            if (board[0] == 1) {
                return 1
            } else if (board[0] == 2) {
                return 2
            }
        }

        if (board[1] == board[4] && board[1] == board[7]) {
            if (board[1] == 1) {
                return 1
            } else if (board[1] == 2) {
                return 2
            }
        }

        if (board[2] == board[5] && board[2] == board[8]) {
            if (board[2] == 1) {
                return 1
            } else if (board[2] == 2) {
                return 2
            }
        }

        //main diagonal
        if (board[0] == board[4] && board[0] == board[8]) {
            if (board[0] == 1) {
                return 1
            } else if (board[0] == 2) {
                return 2
            }
        }

        if (board[2] == board[4] && board[2] == board[6]) {
            if (board[2] == 1) {
                return 1
            } else if (board[2] == 2) {
                return 2
            }
        }

        return 0
    }

}