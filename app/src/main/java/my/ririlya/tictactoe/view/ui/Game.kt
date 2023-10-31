package my.ririlya.tictactoe.view.ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableRow
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import my.ririlya.tictactoe.R
import my.ririlya.tictactoe.databinding.FragmentGameBinding
import my.ririlya.tictactoe.enum.Board
import my.ririlya.tictactoe.enum.Turn
import my.ririlya.tictactoe.model.model.TicTacToe3x3
import my.ririlya.tictactoe.model.model.TicTacToe4x4
import my.ririlya.tictactoe.model.model.TicTacToe5x5
import my.ririlya.tictactoe.util.PartyPreset
import my.ririlya.tictactoe.util.toPx

class Game : Fragment() {


    private var gameState = mutableListOf<Turn>()
    private var buttonTicTacToe = mutableListOf<Button>()
    private var random = (0..1).random()
    private var currentTurn: Turn = if (random == 1) Turn.NOUGHT else Turn.CROSS
    private var currentBoardType: Board = Board.BOARD3X3
    private var currentBoardType2 = when (currentBoardType) {
        Board.BOARD4X4 -> TicTacToe4x4()
        Board.BOARD5X5 -> TicTacToe5x5()
        else -> TicTacToe3x3()
    }
    private var gameEnd: Boolean = false
    val args: GameArgs by navArgs()
    var count_xx:Int = 0
    var count_oo: Int = 0

    private lateinit var gameBinding : FragmentGameBinding
    private val binding get() = gameBinding

    companion object {
        const val NOUGHT = "O"
        const val CROSS = "X"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentBoardType = args.boardType
        currentBoardType2 = when (currentBoardType) {
            Board.BOARD4X4 -> TicTacToe4x4()
            Board.BOARD5X5 -> TicTacToe5x5()
            else -> TicTacToe3x3()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        gameBinding = FragmentGameBinding.inflate(inflater, container, false)
        return gameBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spawnButton()
        resetGame()

        binding.ooScore.text = resources.getString(R.string.xx_score, NOUGHT, count_oo.toString())
        binding.xxScore.text = resources.getString(R.string.xx_score, CROSS, count_xx.toString())
        binding.btnReset.setOnClickListener {
            showDialogAlert(resources.getString(R.string.reset_the_game),
                resources.getString(R.string.reset), fun() {
                resetGame()
            })
        }

        binding.btnMenu.setOnClickListener {
            showDialogAlert(resources.getString(R.string.go_back_menu),
                resources.getString(R.string.ok), fun() {
                Navigation.findNavController(view).navigate(GameDirections.actionGameToHome())
            })
        }
    }

    private fun spawnButton() {
        val length: Int = currentBoardType2.getCount()
        var count = 0
        for (i in 0 until length) {
            val tableRow = TableRow(requireContext())
            tableRow.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            tableRow.gravity = Gravity.CENTER

            for (j in 0 until length) {

                val button = Button(requireContext())
                button.tag = count
                button.setBackgroundColor(resources.getColor(R.color.bgBox, null))
                button.textSize = 60.0f
                button.setTextColor(resources.getColor(R.color.white, null))
                button.setOnClickListener {
                    onButtonClick(it)
                }
                tableRow.addView(button)
                button.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    height = currentBoardType2.getBoxSize()
                    width = currentBoardType2.getBoxSize()
                    setMargins((5).toPx, (5).toPx, 0, 0)
                }
                buttonTicTacToe.add(button)
                count++
            }
            binding.mainLayout.addView(tableRow)
            if (i == 0) {
                tableRow.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    setMargins(0, (10).toPx, 0, 0)
                }
            }

        }
    }

    private fun resetGame() {
        binding.konfettiView.reset()
        random = (0..1).random()
        displayTurnLabel()
        gameState = mutableListOf()
        for (i in 0 until buttonTicTacToe.size) {
            gameState.add(Turn.NOT_SET)
            buttonTicTacToe[i].text = ""
        }
        gameEnd = false
    }

    fun onButtonClick(view: View) {
        /// if its not button we return
        if (view !is Button)
            return

        if (!gameEnd)
        {
            changeButtonState(view)
            checkAnyoneWon()
            changeTurnState()
        }

    }


    private fun changeButtonState(button: Button) {
        if (button.text != "")
            return
        gameState[button.tag.toString().toInt()] = currentTurn
        button.text = returnTextBtn()
    }

    private fun checkAnyoneWon() {
        if (currentTurn == Turn.CROSS && checkGameState(currentTurn)) {
            binding.turnText.text = resources.getString(R.string.xx_win, CROSS)
            count_xx++
            binding.xxScore.text = resources.getString(R.string.xx_score, CROSS, count_xx.toString())
            gameEnd = true
            binding.konfettiView.start(PartyPreset.parade())
        } else if (currentTurn == Turn.NOUGHT && checkGameState(currentTurn)) {
            binding.turnText.text = resources.getString(R.string.xx_win, NOUGHT)
            count_oo++
            binding.ooScore.text = resources.getString(R.string.xx_score, NOUGHT, count_oo.toString())
            gameEnd = true
            binding.konfettiView.start(PartyPreset.parade())
        } else if (isAllBoardOccupy()) {
            binding.turnText.text = resources.getString(R.string.draw)
            gameEnd = true
        }
    }

    private fun checkGameState(currentTurn: Turn): Boolean {
        return currentBoardType2.getWinningCombo()
            .filter { it.all { idx -> gameState[idx] == currentTurn } }.any()
    }


    private fun isAllBoardOccupy(): Boolean {
        for (button in buttonTicTacToe) {
            if (button.text == "")
                return false
        }
        return true
    }

    private fun changeTurnState() {
        if (currentTurn == Turn.NOUGHT) {
            currentTurn = Turn.CROSS
        } else if (currentTurn == Turn.CROSS) {
            currentTurn = Turn.NOUGHT
        }
        displayTurnLabel()
    }


    private fun displayTurnLabel() {
        binding.turnText.text = resources.getString(R.string.xx_turn, returnTextBtn())
    }

    private fun returnTextBtn(): String {
        return if (currentTurn == Turn.CROSS)
            CROSS
        else
            NOUGHT
    }


    private fun showDialogAlert(message: String, positiveText: String, codeBlock: () -> (Unit)) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setPositiveButton(positiveText)
            { _, _ ->
                codeBlock()
            }
            .setNegativeButton(resources.getString(R.string.cancel))
            { _, _ ->

            }
            .setCancelable(false)
            .show()
    }

}