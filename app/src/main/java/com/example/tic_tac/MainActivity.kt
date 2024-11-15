package com.example.tic_tac

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isPlayerOneTurn = true  // Player 1 starts as 'X'
    private val gameBoard = arrayOfNulls<String>(9)  // 1D array to store 9 cells

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // This method is called when a cell (ImageView) is clicked
    fun onCellClicked(view: View) {
        val imageView = view as ImageView
        val cellIndex = getCellIndex(imageView.id)  // Get the index of the clicked cell

        // If the cell is already filled, return
        if (gameBoard[cellIndex] != null) {
            return
        }

        // Mark the cell with either 'X' or 'O'
        val symbol = if (isPlayerOneTurn) "X" else "O"
        gameBoard[cellIndex] = symbol

        // Set the image based on the current player's turn
        val imageRes = if (isPlayerOneTurn) R.drawable.x else R.drawable.o
        imageView.setImageResource(imageRes)

        // Check for a winner after each move
        if (checkWinner(symbol)) {
            Toast.makeText(this, "Player ${if (isPlayerOneTurn) "X" else "O"} wins!", Toast.LENGTH_LONG).show()
            resetGame()  // Reset the game after a win
            return
        }

        // Check for a draw
        if (isDraw()) {
            Toast.makeText(this, "It's a draw!", Toast.LENGTH_LONG).show()
            resetGame()  // Reset the game after a draw
            return
        }

        // Toggle the player's turn
        isPlayerOneTurn = !isPlayerOneTurn
    }

    // Helper method to get the cell index based on the view ID
    private fun getCellIndex(viewId: Int): Int {
        return when (viewId) {
            R.id.cell0 -> 0
            R.id.cell1 -> 1
            R.id.cell2 -> 2
            R.id.cell3 -> 3
            R.id.cell4 -> 4
            R.id.cell5 -> 5
            R.id.cell6 -> 6
            R.id.cell7 -> 7
            R.id.cell8 -> 8
            else -> -1
        }
    }

    // Helper method to check for a winner
    private fun checkWinner(symbol: String): Boolean {
        // Check rows, columns, and diagonals
        return (gameBoard[0] == symbol && gameBoard[1] == symbol && gameBoard[2] == symbol) ||  // Row 1
                (gameBoard[3] == symbol && gameBoard[4] == symbol && gameBoard[5] == symbol) ||  // Row 2
                (gameBoard[6] == symbol && gameBoard[7] == symbol && gameBoard[8] == symbol) ||  // Row 3
                (gameBoard[0] == symbol && gameBoard[3] == symbol && gameBoard[6] == symbol) ||  // Column 1
                (gameBoard[1] == symbol && gameBoard[4] == symbol && gameBoard[7] == symbol) ||  // Column 2
                (gameBoard[2] == symbol && gameBoard[5] == symbol && gameBoard[8] == symbol) ||  // Column 3
                (gameBoard[0] == symbol && gameBoard[4] == symbol && gameBoard[8] == symbol) ||  // Diagonal 1
                (gameBoard[2] == symbol && gameBoard[4] == symbol && gameBoard[6] == symbol)     // Diagonal 2
    }

    // Helper method to check if the game is a draw (all cells are filled)
    private fun isDraw(): Boolean {
        return !gameBoard.contains(null)
    }

    // Reset the game state after a win or draw
    private fun resetGame() {
        // Reset the game board
        gameBoard.fill(null)

        // Reset all ImageViews
        findViewById<ImageView>(R.id.cell0).setImageResource(R.drawable.w)
        findViewById<ImageView>(R.id.cell1).setImageResource(R.drawable.w)
        findViewById<ImageView>(R.id.cell2).setImageResource(R.drawable.w)
        findViewById<ImageView>(R.id.cell3).setImageResource(R.drawable.w)
        findViewById<ImageView>(R.id.cell4).setImageResource(R.drawable.w)
        findViewById<ImageView>(R.id.cell5).setImageResource(R.drawable.w)
        findViewById<ImageView>(R.id.cell6).setImageResource(R.drawable.w)
        findViewById<ImageView>(R.id.cell7).setImageResource(R.drawable.w)
        findViewById<ImageView>(R.id.cell8).setImageResource(R.drawable.w)
    }
}
