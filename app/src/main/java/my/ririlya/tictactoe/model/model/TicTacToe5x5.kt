package my.ririlya.tictactoe.model.model

import my.ririlya.tictactoe.enum.Board
import my.ririlya.tictactoe.model.interfaces.TicTacToeBoard
import my.ririlya.tictactoe.util.toPx

class TicTacToe5x5 : TicTacToeBoard {
    private val count: Int = 5
    private val boardType: Board = Board.BOARD5X5
    private val boxSize: Int = (50).toPx

    override fun getCount(): Int {
        return count
    }

    override fun getWinningCombo(): Array<IntArray> {
        return arrayOf(
            intArrayOf(0, 1, 2, 3, 4),
            intArrayOf(5, 6, 7, 8, 9),
            intArrayOf(10, 11, 12, 13, 14),
            intArrayOf(15, 16, 17, 18, 19),
            intArrayOf(20, 21, 22, 23, 24),

            intArrayOf(0, 5, 10, 15, 20),
            intArrayOf(1, 6, 11, 16, 21),
            intArrayOf(2, 7, 12, 17, 22),
            intArrayOf(3, 8, 13, 18, 23),
            intArrayOf(4, 9, 14, 19, 24),


            intArrayOf(0, 6, 12, 18, 24),
            intArrayOf(4, 8, 12, 16, 20),
        )
    }

    override fun getBoardType(): Board {
        return boardType
    }

    override fun getBoxSize(): Int {
        return boxSize
    }
}