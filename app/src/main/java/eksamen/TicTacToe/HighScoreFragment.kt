package eksamen.TicTacToe

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.fragment_high_score.*
import kotlinx.android.synthetic.main.list_item.*

class HighScoreFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //val adapter = HighScoreAdapter(this.activity, R.layout.list_item, arrayList)
        //listView.adapter = adapter
        //Log.d("test highscore mode", keys.toString())
        /*val adapter = HighScoreAdapter(this.activity, R.layout.list_item, keys)
        val listView: ListView = listv
        listView.adapter = adapter

*/
        return inflater!!.inflate(R.layout.fragment_high_score, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val pref = context!!.getSharedPreferences("score", Context.MODE_PRIVATE)
        //val prefEditor = pref?.edit()
        //prefEditor?.apply()
        val map = pref?.all
        //val arrayMap = ArrayList<Map<String, String>>()
        //map?.toSortedMap()
        val keys  = ArrayList(map?.keys)
        val values = arrayListOf(map?.values.toString())
        //val arrayList = arrayListOf<String>("1", "2", "3")

        //val adapter = ArrayAdapter(activity?.applicationContext, R.layout.list_item, keys)
        val adapter = HighScoreAdapter(activity?.applicationContext!!, keys, values)

        Log.d("testing index", map!!["User"].toString())
        Log.d("testing key index", keys[0].toString())
        listView.adapter = adapter
        super.onActivityCreated(savedInstanceState)
        map?.forEach { _ ->
            Log.d("", map.values.toString())
        }
        values.forEach { _ ->
            Log.d("valuesTring", values.toString())
        }
        values.forEach { _ ->
            Log.d("valuesTring", values.toString())
        }
        Log.d("testing", values.toString())
    }


}