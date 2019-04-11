package eksamen.TicTacToe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.list_score.view.*

class HighScoreAdapter(val context: Context, private val name: ArrayList<String>, private val score : ArrayList<String>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    /*fun HighScoreAdapter(context : Context, name : ArrayList<String>, score : ArrayList<String>) {
        this.context = context
        this.name = name
        this.score = score
    }*/

    override fun getItem(position: Int): Any {
        /*if(position >= name.size) {
            return score[position]
        }*/
        return position
    }

    override fun getItemId(position: Int): Long {
        if(position >= name.size) {
            return score[position].toLong()
        }
        return name[position].toLong()
    }

    override fun getCount(): Int {
        return name.size

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /*if (convertView == null) {
            return inflater.inflate(R.layout.lixst_score, parent, false)
        }*/

        val playerName = name[position]
        var playerScore = score[0]
        val playerTextView = inflater.inflate(R.layout.list_score, parent, false)

        playerTextView.userName.text = playerName.toString()
        playerTextView.userScore.text = playerScore.toString()

        return playerTextView

    }
}