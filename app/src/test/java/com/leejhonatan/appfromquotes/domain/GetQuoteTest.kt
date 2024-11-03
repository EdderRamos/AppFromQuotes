package com.leejhonatan.appfromquotes.domain

import com.leejhonatan.appfromquotes.data.QuoteRepository
import com.leejhonatan.appfromquotes.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuoteTest {

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getQuote: GetQuote

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuote = GetQuote(quoteRepository)
    }

    @Test
    fun `when The Api Doesn't Return Anything Then Get Values From Database`() = runBlocking {
        coEvery { quoteRepository.getALlQuoteFromApi() } returns emptyList()
        getQuote()
        coVerify(exactly = 1) { quoteRepository.getAllQuoteFromDataBase() }

    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        val myList = listOf(Quote("La vida es maravillosa", author = "Edder"))
        coEvery { quoteRepository.getALlQuoteFromApi() } returns myList

        val result = getQuote()
        coVerify(exactly = 1) { quoteRepository.clearAllQuote() }
        coVerify(exactly = 1) { quoteRepository.insertAllQuote(any()) }

        coVerify(exactly = 0) { quoteRepository.getAllQuoteFromDataBase() }
        assert(myList == result)

    }
}