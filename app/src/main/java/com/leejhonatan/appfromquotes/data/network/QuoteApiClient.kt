package com.leejhonatan.appfromquotes.data.network

import com.leejhonatan.appfromquotes.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface QuoteApiClient{
    @GET("/.json")
    suspend fun getQuote(): Response<List<QuoteModel>>
}