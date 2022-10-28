package com.leejhonatan.appfromquotes.domain

import com.leejhonatan.appfromquotes.data.QuoteRepository
import com.leejhonatan.appfromquotes.domain.model.Quote
import javax.inject.Inject

class GetRandomQuote @Inject constructor(private val repository: QuoteRepository) {
    suspend operator fun invoke(): Quote? {
        val quote = repository.getAllQuoteFromDataBase()
        if (!quote.isNullOrEmpty()){
            val randomNumber = (quote.indices).random()
            return quote[randomNumber]
        }
        return null

    }
}