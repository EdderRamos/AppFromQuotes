package com.leejhonatan.appfromquotes.domain

import com.leejhonatan.appfromquotes.data.QuoteRepository
import com.leejhonatan.appfromquotes.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomQuoteTest {
    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository
    lateinit var getRandomQuote: GetRandomQuote

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomQuote = GetRandomQuote(quoteRepository)
    }

    @Test
    fun `when database is empty then return null`() = runBlocking {
        //Given
        coEvery { quoteRepository.getAllQuoteFromDataBase() } returns emptyList()
        //when
        val response = getRandomQuote()
        //then
        assert(response == null)
    }

    @Test
    fun `when database is not empty then return quote`() = runBlocking {
        //Given
        val quoteList = listOf(Quote("Pruebas", "Edde Ramos"))
        coEvery { quoteRepository.getAllQuoteFromDataBase() } returns quoteList

        val result = getRandomQuote()

        assert(result == quoteList.first())
    }
}