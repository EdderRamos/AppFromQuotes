package com.leejhonatan.appfromquotes.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.leejhonatan.appfromquotes.databinding.ActivityMainBinding
import com.leejhonatan.appfromquotes.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()

        quoteViewModel.isLoading.observe(this){
            binding.progress.isVisible = it
        }
        quoteViewModel.quoteModel.observe(this){
            binding.tvQuote.text = it.quote
            binding.tvAuthor.text = it.author
        }
        binding.viewContainer.setOnClickListener {
            quoteViewModel.randomQuote()
        }
        binding.btnDarkMode.setOnClickListener{
            quoteViewModel.darkMode()
        }
    }
}