package com.example.webviewapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.webviewapp.ViewModelFactory
import com.example.webviewapp.viewmodels.WebViewViewModel
import com.example.webviewapp.databinding.ActivityMainBinding
import com.example.webviewapp.viewmodels.State

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val webViewViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(this))[WebViewViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.getDataBtn.setOnClickListener {
            webViewViewModel.getWebView()
        }
    }

    private fun setupObservers() {
        webViewViewModel.apply {
            numLiveData.observe(this@MainActivity) {
                //binding.getDataBtn.text = it.toString()
                binding.webView.loadUrl("https://www.google.com/")
            }

            stateLiveData.observe(this@MainActivity) {
                binding.loader.visibility = when (it) {
                    State.LOADING -> View.VISIBLE
                    State.IDLE,
                    null -> View.GONE
                }
            }
        }
    }
}