package com.leejhonatan.appfromquotes.domain

import com.leejhonatan.appfromquotes.data.QuoteRepository
import com.leejhonatan.appfromquotes.data.database.entities.toDataBase
import com.leejhonatan.appfromquotes.domain.model.Quote
import javax.inject.Inject

class GetQuote @Inject constructor(private val repository: QuoteRepository) {
    suspend operator fun invoke(): List<Quote>{

        val quote = repository.getALlQuoteFromApi()
        return if(quote.isNotEmpty()){
            repository.clearAllQuote()
            repository.insertAllQuote(quote.map { it.toDataBase() })
            quote
        } else {
            repository.getAllQuoteFromDataBase()
        }
    }
}