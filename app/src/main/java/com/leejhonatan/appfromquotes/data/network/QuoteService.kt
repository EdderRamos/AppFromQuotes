package com.leejhonatan.appfromquotes.data.network

import com.leejhonatan.appfromquotes.data.model.QuoteModel
import javax.inject.Inject

class QuoteService @Inject constructor(private val api: QuoteApiClient) {

    suspend fun getAllQuote(): List<QuoteModel> {
        val response = api.getQuote()
        return response.body() ?: emptyList()
    }
}