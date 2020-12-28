package cn.edu.fragment.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import cn.edu.fragment.Adapter.CardAdapter
import cn.edu.fragment.R
import cn.edu.fragment.model.CardMatchingGame
import kotlinx.android.synthetic.main.fragment_game.*
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception

var game: CardMatchingGame = CardMatchingGame(24)

class GameFragment :Fragment() {
    val cardButtons = mutableListOf<Button>()
    lateinit var adapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rgame = loadData()
        if (rgame != null){
            game = rgame
        }else{
            game = CardMatchingGame(24)
        }
        adapter = CardAdapter(game)
        recyclerView.adapter = adapter
        val configuration = resources.configuration
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager = GridLayoutManager(activity,6)
        }else{
            val gridLayoutManager = GridLayoutManager(activity,4)
            recyclerView.layoutManager = gridLayoutManager
        }
        updataUI()
        adapter.setOnClickListener {
            game.chooseCardAtIndex(it)
            updataUI()
        }
        button_reset.setOnClickListener {
            game.reset()
            updataUI()
        }
    }

    fun updataUI(){
        adapter.notifyDataSetChanged()
        score.text = String.format("%s%d","Score",game.score)
    }

    override fun onStop() {
        super.onStop()
        saveData()
    }

    fun saveData(){
        try{
            val output = activity?.openFileOutput("data",AppCompatActivity.MODE_PRIVATE)
            val objectOutputStream = ObjectOutputStream(output)
            objectOutputStream.writeObject(game)
            objectOutputStream.close()
            output?.close()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun loadData() : CardMatchingGame?{
        try {
            val input = activity?.openFileInput("data")
            val objectInputStream = ObjectInputStream(input)
            val game = objectInputStream.readObject() as CardMatchingGame
            objectInputStream.close()
            input?.close()
            return game
        }catch (e: Exception){
            e.printStackTrace()
            return null
        }
    }
    companion object{
        lateinit var game: CardMatchingGame
        @JvmStatic
        fun newInstance() = GameFragment()
    }
}