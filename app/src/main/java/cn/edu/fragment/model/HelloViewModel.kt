package cn.edu.fragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HelloViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "欢迎来到APP!"
    }
    val text: LiveData<String> = _text
}