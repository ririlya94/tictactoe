package my.ririlya.tictactoe.model.interfaces

import my.ririlya.tictactoe.enum.Board

interface TicTacToeBoard {
    fun getCount(): Int
    fun getWinningCombo(): Array<IntArray>
    fun getBoardType(): Board
    fun getBoxSize(): Int
}