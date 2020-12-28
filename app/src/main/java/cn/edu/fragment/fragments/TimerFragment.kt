package cn.edu.fragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.edu.fragment.R
import cn.edu.fragment.model.WatchViewModel
import kotlinx.android.synthetic.main.fragment_timer.*

class TimerFragment:Fragment(){

    lateinit var viewModel: WatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timer,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            WatchViewModel::class.java)

        viewModel.seconds.observe(this, Observer {
            val hours = it/3600
            val minutes = (it % 3600)/60
            val secs = it % 60
            textView_timer.text = String.format(getString(R.string.time),hours,minutes,secs)
        })

        button_start.setOnClickListener {
            viewModel.start()
        }
        button_stop.setOnClickListener {
            viewModel.stop()
        }
        button_restart.setOnClickListener {
            viewModel.restart()
        }
    }

    companion object{
        fun newInstance() = TimerFragment()
    }
}