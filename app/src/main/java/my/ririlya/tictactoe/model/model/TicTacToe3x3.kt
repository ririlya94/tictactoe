package my.ririlya.tictactoe.model.model

import my.ririlya.tictactoe.enum.Board
import my.ririlya.tictactoe.model.interfaces.TicTacToeBoard
import my.ririlya.tictactoe.util.toPx

class TicTacToe3x3() : TicTacToeBoard {
    private val count: Int = 3
    private val boardType: Board = Board.BOARD3X3
    private val boxSize: Int = (100).toPx

    override fun getCount(): Int {
        return count
    }

    override fun getWinningCombo(): Array<IntArray> {
        return arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),

            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),

            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )
    }

    override fun getBoardType(): Board {
        return boardType
    }

    override fun getBoxSize(): Int {
        return boxSize
    }

}