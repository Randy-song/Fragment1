package cn.edu.fragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import cn.edu.fragment.R

class HelloFragment:Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hello,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.button2)
        val textView = view.findViewById<TextView>(R.id.textView)
        button.setOnClickListener {
            textView.text = "欢迎来到APP"
        }
    }

    companion object{
        fun newInstance() = HelloFragment()
    }
}