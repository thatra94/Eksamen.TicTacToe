package eksamen.TicTacToe

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_game.*
import java.lang.Exception
import java.util.*

class GameFragment : Fragment(), View.OnClickListener {

    private lateinit var sharedView: SharedViewModel
    private var playerOneMoves = ArrayList<Int>()
    private var playerTwoMoves = ArrayList<Int>()
    private var activePlayer = 1
    private var winner = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        timer.start()

        square1.setOnClickListener(this)
        square2.setOnClickListener(this)
        square3.setOnClickListener(this)
        square4.setOnClickListener(this)
        square5.setOnClickListener(this)
        square6.setOnClickListener(this)
        square7.setOnClickListener(this)
        square8.setOnClickListener(this)
        square9.setOnClickListener(this)

        resetBtn.setOnClickListener{
            reset()
        }
        getNames()
    }

    private fun getNames() {
        sharedView = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception ("invalid")
        sharedView.playerOne.observe(this, Observer {
            textView1.text = sharedView.playerOne.value
        })
        sharedView.playerTwo.observe(this, Observer {
            textView2.text = sharedView.playerTwo.value
        })
     }

    override fun onClick(view: View?) {
        checkForWinner()
        val squareSelected = view as Button
        var squareID = 0
        when(squareSelected.id) {
            R.id.square1 -> squareID = 1
            R.id.square2 -> squareID = 2
            R.id.square3 -> squareID = 3
            R.id.square4 -> squareID = 4
            R.id.square5 -> squareID = 5
            R.id.square6 -> squareID = 6
            R.id.square7 -> squareID = 7
            R.id.square8 -> squareID = 8
            R.id.square9 -> squareID = 9
        }
        playGame(squareID, squareSelected)
        Log.d("test", squareID.toString())
    }

    private fun playGame(squareID: Int, squareSelected: Button) {
        Log.d("checkForWinner", "winner")
        checkForWinner()
            if (activePlayer == 1) {
                textView1.setTextColor(Color.BLUE)
                squareSelected.text = "X"
                squareSelected.setTextColor(Color.parseColor("#ff471a"))
                textView1.setTextColor(Color.parseColor("#ffffff"))
                textView2.setTextColor(Color.parseColor("#00cc00"))
                playerOneMoves.add(squareID)
                activePlayer = 2
                playerOneMoves.forEach { _ ->
                    Log.d("", playerOneMoves.toString())
                }

                if (sharedView.playerTwo.value == "TTTBot" && winner == -1) {
                    autoPlay()
                }
            } else {
                squareSelected.text = "O"
                squareSelected.setTextColor(Color.parseColor("#00cc00"))
                textView1.setTextColor(Color.parseColor("#ff471a"))
                textView2.setTextColor(Color.parseColor("#ffffff"))
                playerTwoMoves.add(squareID)
                activePlayer = 1
            }

            squareSelected.isEnabled = false
        }

    private fun reset() {
        winner = -1
        this.playerOneMoves = ArrayList()
        this.playerTwoMoves = ArrayList()
        val fr = fragmentManager!!.beginTransaction()
        fr.detach(this)
        fr.attach(this)
        fr.commit()
    }

    private fun checkForWinner() {
        Log.d("checkForWinner", "function")
        if (playerOneMoves.containsAll(listOf(1, 2, 3)) // row 1
            || playerOneMoves.containsAll(listOf(4, 5, 6)) // row 2
            || playerOneMoves.containsAll(listOf(7, 8, 9)) // row 3
            || playerOneMoves.containsAll(listOf(1, 4, 7)) // column 1
            || playerOneMoves.containsAll(listOf(2, 5, 8)) // column 2
            || playerOneMoves.containsAll(listOf(3, 6, 9)) // column 3
            || playerOneMoves.containsAll(listOf(1, 5, 9)) // diagonal 1
            || playerOneMoves.containsAll(listOf(3, 5, 7)) // diagonal 2
        ) {
            winner = 1
            saveHighScore(sharedView.playerOne.value.toString())
        }
        if (playerTwoMoves.containsAll(listOf(1, 2, 3)) // row 1
            || playerTwoMoves.containsAll(listOf(4, 5, 6)) // row 2
            || playerTwoMoves.containsAll(listOf(7, 8, 9)) // row 3
            || playerTwoMoves.containsAll(listOf(1, 4, 7)) // column 1
            || playerTwoMoves.containsAll(listOf(2, 5, 8)) // column 2
            || playerTwoMoves.containsAll(listOf(3, 6, 9)) // column 3
            || playerTwoMoves.containsAll(listOf(1, 5, 9)) // diagonal 1
            || playerTwoMoves.containsAll(listOf(3, 5, 7)) // diagonal 2
        ) {
            winner = 2
            saveHighScore(sharedView.playerTwo.value.toString())
        }

        if (winner != -1) {
            if (winner == 1) {
                Toast.makeText(activity, sharedView.playerOne.value.toString() + " won the game", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(activity, sharedView.playerTwo.value.toString() + " won the game", Toast.LENGTH_LONG)
                    .show()
            }
            Log.d("checkForWinner", "disable")
            disableButtons()
            timer.stop()
        }
    }

    private fun disableButtons() {
        square1.isEnabled = false
        square2.isEnabled = false
        square3.isEnabled = false
        square4.isEnabled = false
        square5.isEnabled = false
        square6.isEnabled = false
        square7.isEnabled = false
        square8.isEnabled = false
        square9.isEnabled = false
    }

    private fun autoPlay() {
        Log.d("autoplay", "test")
        if(winner == -1) {
            var emptySquare = ArrayList<Int>()
            for (squareID in 1..9) {
                if (!(playerOneMoves.contains(squareID)) && !(playerTwoMoves.contains(squareID))) {
                    emptySquare.add(squareID)
                }
            }
            if (emptySquare.size != 0) {
                val random = Random()
                val randomIndex = random.nextInt(emptySquare.size)
                val squareID = emptySquare[randomIndex]

                val buttonSelected: Button
                buttonSelected = when (squareID) {
                    1 -> square1
                    2 -> square2
                    3 -> square3
                    4 -> square4
                    5 -> square5
                    6 -> square6
                    7 -> square7
                    8 -> square8
                    9 -> square9
                    else -> square1
                }
                playGame(squareID, buttonSelected)
            }
        }
    }


    private fun saveHighScore(playerName: String) {

        val pref = activity?.getSharedPreferences("score", Context.MODE_PRIVATE)
        val map = pref!!.all
        val playerScore = pref.getInt(playerName, 0)
        val editor = pref.edit()
        editor?.putInt(playerName, playerScore.inc())
        editor?.apply()

        map.forEach { (key, value) ->
            println("$key = $value")
        }
    }
}


