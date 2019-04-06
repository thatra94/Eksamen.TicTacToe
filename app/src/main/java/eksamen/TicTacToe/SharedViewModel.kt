package eksamen.TicTacToe

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log

class SharedViewModel : ViewModel() {

    var playerOne = MutableLiveData<CharSequence>()
    var playerTwo = MutableLiveData<CharSequence>()
    var score = mutableMapOf<String, Int>()

    fun setPlayerOne(playerOneName: CharSequence) {
        playerOne.value = playerOneName
    }

    fun setPlayerTwo(playerTwoName: CharSequence) {
        playerTwo.value = playerTwoName
    }

    fun addScore(playerName: String) {

        var count = score[playerName]
        if (count == null) {
            count = 0
        }
        score[playerName] = count.inc()
        score.forEach { (key, value) ->
            println("$key = $value")
        }

        /*var count = score[playerName]
        if (score.contains(playerName)) {
            var playerScore = score.getValue(playerName)+1
            score[playerName] = playerScore
        }else {
            score[playerName] = 1
        }
        score.forEach() {
            (key, value) -> println("$key = $value")
        }*/
    }

}