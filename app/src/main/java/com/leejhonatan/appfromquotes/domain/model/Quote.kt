package com.leejhonatan.appfromquotes.domain.model

import com.leejhonatan.appfromquotes.data.database.entities.QuoteEntity
import com.leejhonatan.appfromquotes.data.model.QuoteModel

data class Quote(val quote: String, val author: String)

fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntity.toDomain() = Quote(quote, author)
