package eksamen.TicTacToe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.list_score.view.*

class HighScoreAdapter(val context: Context, private val name: ArrayList<String>, private val score: ArrayList<Any?>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getItem(position: Int): Any {

        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return name.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val playerName = name[position]
        var playerScore = score[position]
        val playerTextView = inflater.inflate(R.layout.list_score, parent, false)

        playerTextView.userName.text = playerName.toString()
        playerTextView.userScore.text = playerScore.toString()

        return playerTextView

    }
}