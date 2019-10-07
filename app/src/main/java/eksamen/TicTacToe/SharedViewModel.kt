package eksamen.TicTacToe

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log

class SharedViewModel : ViewModel() {

    var playerOne = MutableLiveData<CharSequence>()
    var playerTwo = MutableLiveData<CharSequence>()

    fun setPlayerOne(playerOneName: CharSequence) {
        playerOne.value = playerOneName
    }

    fun setPlayerTwo(playerTwoName: CharSequence) {
        playerTwo.value = playerTwoName
    }
}