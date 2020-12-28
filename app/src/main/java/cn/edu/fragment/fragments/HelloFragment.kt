package cn.edu.fragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cn.edu.fragment.R
import cn.edu.fragment.model.HelloViewModel

class HelloFragment : Fragment() {

    private lateinit var helloViewModel: HelloViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        helloViewModel =
                ViewModelProviders.of(this).get(HelloViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_hello, container, false)
        val textView: TextView = root.findViewById(R.id.textView)
        helloViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
            var str = it;
            val button = view?.findViewById<Button>(R.id.button2)
            if (button != null) {
                button.setOnClickListener {
                    textView.text = str
                }
            }
        })
        return root
    }
}