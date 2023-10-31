package my.ririlya.tictactoe.model.model

import my.ririlya.tictactoe.enum.Board
import my.ririlya.tictactoe.model.interfaces.TicTacToeBoard
import my.ririlya.tictactoe.util.toPx

class TicTacToe4x4 : TicTacToeBoard {
    private val count: Int = 4
    private val boardType: Board = Board.BOARD4X4
    private val boxSize: Int = (70).toPx

    override fun getCount(): Int {
        return count
    }

    override fun getWinningCombo(): Array<IntArray> {
        return arrayOf(
            intArrayOf(0, 1, 2, 3),
            intArrayOf(4, 5, 6, 7),
            intArrayOf(8, 9, 10, 11),
            intArrayOf(12, 13, 14, 15),

            intArrayOf(0, 4, 8, 12),
            intArrayOf(1, 5, 9, 13),
            intArrayOf(2, 6, 10, 14),
            intArrayOf(3, 7, 11, 15),

            intArrayOf(3, 6, 9, 12),
            intArrayOf(3, 5, 10, 15)
        )
    }

    override fun getBoardType(): Board {
        return boardType
    }

    override fun getBoxSize(): Int {
        return boxSize
    }
}