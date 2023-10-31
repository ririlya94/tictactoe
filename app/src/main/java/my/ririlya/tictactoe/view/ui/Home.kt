package my.ririlya.tictactoe.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import my.ririlya.tictactoe.databinding.FragmentHomeBinding
import my.ririlya.tictactoe.enum.Board

class Home : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private val binding get() = homeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tictactoe3x3btn.setOnClickListener {
            Navigation.findNavController(view).navigate(HomeDirections.actionHomeToGame(Board.BOARD3X3))
        }
        binding.tictactoe4x4btn.setOnClickListener {
            Navigation.findNavController(view).navigate(HomeDirections.actionHomeToGame(Board.BOARD4X4))
        }
        binding.tictactoe5x5btn.setOnClickListener {
            Navigation.findNavController(view).navigate(HomeDirections.actionHomeToGame(Board.BOARD5X5))
        }

    }


}