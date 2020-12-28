package cn.edu.fragment.model

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WatchViewModel:ViewModel() {
    private var _seconds:MutableLiveData<Int> = MutableLiveData()
    private var running = false
    val seconds:LiveData<Int> = _seconds

    init {
        runtimer()
    }

    fun start(){
        running = true
    }
    fun stop(){
        running = false
    }
    fun restart(){
        running = true
        _seconds.value = 0
    }

    fun runtimer(){
        val handle = Handler()
        val runnable = object :Runnable{
            override fun run() {
                if(running){
                    val sec = _seconds.value ?:0
                    _seconds.value = sec+1
                }
                handle.postDelayed(this,1000)
            }
        }
        handle.post(runnable)
    }
}