package com.leejhonatan.appfromquotes.data

import com.leejhonatan.appfromquotes.data.database.dao.QuoteDao
import com.leejhonatan.appfromquotes.data.database.entities.QuoteEntity
import com.leejhonatan.appfromquotes.data.model.QuoteModel
import com.leejhonatan.appfromquotes.data.network.QuoteService
import com.leejhonatan.appfromquotes.domain.model.Quote
import com.leejhonatan.appfromquotes.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {
    suspend fun getALlQuoteFromApi(): List<Quote> {
        val response: List<QuoteModel> = api.getAllQuote()
        return response.map { it.toDomain() }

    }

    suspend fun getAllQuoteFromDataBase(): List<Quote> {
        val response: List<QuoteEntity> = quoteDao.getAllQuote()
        return response.map { it.toDomain() }
    }

    suspend fun insertAllQuote(quotes: List<QuoteEntity>) {
        quoteDao.insetALl(quotes)
    }
    suspend fun clearAllQuote(){
        quoteDao.deleteAll()
    }

}