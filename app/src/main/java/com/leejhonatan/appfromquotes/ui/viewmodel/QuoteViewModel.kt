package com.leejhonatan.appfromquotes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leejhonatan.appfromquotes.domain.GetDarkMode
import com.leejhonatan.appfromquotes.domain.GetQuote
import com.leejhonatan.appfromquotes.domain.GetRandomQuote
import com.leejhonatan.appfromquotes.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuote: GetQuote,
    private val getRandom: GetRandomQuote,
    private val getDarkMode: GetDarkMode
) : ViewModel() {
    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()
    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuote()
            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val quote = getRandom()
            if (quote != null) {
                quoteModel.postValue(quote)
            }

            isLoading.postValue(false)
        }

    }

    fun darkMode() = getDarkMode()
}