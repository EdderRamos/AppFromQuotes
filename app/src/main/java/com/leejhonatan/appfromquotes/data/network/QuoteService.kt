package com.leejhonatan.appfromquotes.data.network

import com.leejhonatan.appfromquotes.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(private val api: QuoteApiClient) {

    suspend fun getAllQuote(): List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getQuote()
            response.body() ?: emptyList()
        }
    }
}