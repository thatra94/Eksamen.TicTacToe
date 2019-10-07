package eksamen.TicTacToe

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_start_menu.*
import java.lang.Exception

class StartMenuFragment : Fragment() {

    private lateinit var sharedView: SharedViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        activity?.let {
            sharedView = ViewModelProviders.of(it).get(SharedViewModel::class.java)
        }

        btnStart.setOnClickListener{
            start()
        }
        btnHighScore.setOnClickListener{
           highScore()
        }

        btnExit.setOnClickListener {
            System.exit(0)

        }
    }

    private fun start() {
        val player1 = playerOneName.text.toString()
        val player2 = playerTwoName.text.toString()
        sharedView.setPlayerOne(player1)
        sharedView.setPlayerTwo(player2)
        val fr = fragmentManager!!.beginTransaction()
        fr.replace(R.id.fragment_container, GameFragment())
        fr.addToBackStack(null)
        fr.commit()
        Log.d("test", player1)
    }

    private fun highScore() {
        val fr = fragmentManager!!.beginTransaction()
        fr.replace(R.id.fragment_container, HighScoreFragment())
        fr.addToBackStack(null)
        fr.commit()
    }
}