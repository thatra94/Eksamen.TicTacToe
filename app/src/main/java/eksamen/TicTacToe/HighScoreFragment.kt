package eksamen.TicTacToe

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_high_score.*

class HighScoreFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_high_score, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val pref = context!!.getSharedPreferences("score", Context.MODE_PRIVATE)
        val map = pref?.all
        val keys  = ArrayList(map!!.keys)
        val values = ArrayList(map.values)
        val adapter = HighScoreAdapter(activity?.applicationContext!!, keys, values)
        listView.adapter = adapter
        super.onActivityCreated(savedInstanceState)
    }



}