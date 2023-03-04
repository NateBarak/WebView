package com.example.webviewapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webviewapp.repos.WebViewRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class State {
    IDLE,
    LOADING
}

class WebViewViewModel(private val webViewRepo: WebViewRepo, app: Application): ViewModel() {

    private val _numLiveData = MutableLiveData<Int>()
    val numLiveData: LiveData<Int> = _numLiveData

    private val _stateLiveData = MutableLiveData(State.IDLE)
    val stateLiveData: LiveData<State> = _stateLiveData

    fun getWebView() {
        viewModelScope.launch(Dispatchers.IO) {
            val randomNumber: Call<Int> = webViewRepo.getRandomNumber()

            _stateLiveData.postValue(State.LOADING)
            randomNumber.enqueue(object: Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    Log.d(WebViewViewModel::class.java.name, "Response - ${response.body()}")
                    _stateLiveData.postValue(State.IDLE)

                    if (response.isSuccessful) {
                        _numLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.d(WebViewViewModel::class.java.name, "Failed - ${t.message}")
                    _stateLiveData.postValue(State.IDLE)
                }
            })
        }
    }
}