package com.leejhonatan.appfromquotes.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.leejhonatan.appfromquotes.domain.GetDarkMode
import com.leejhonatan.appfromquotes.domain.GetQuote
import com.leejhonatan.appfromquotes.domain.GetRandomQuote
import com.leejhonatan.appfromquotes.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteViewModelTest {

    @RelaxedMockK
    private lateinit var getQuote: GetQuote

    @RelaxedMockK
    private lateinit var getRandom: GetRandomQuote

    @RelaxedMockK
    private lateinit var getDarkMode: GetDarkMode

    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuote, getRandom, getDarkMode)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when view model is created at the first time, get all quotes and set first value`() =
        runTest {

            val quoteList = listOf(Quote("test", "test_author"), Quote("test2", "test_author2"))
            coEvery { getQuote() } returns quoteList

            quoteViewModel.onCreate()

            assert(quoteViewModel.quoteModel.value == quoteList.first())
        }

    @Test
    fun `when random quote use case return a quote set on the livedata`() = runTest {
        val quote = Quote("test", "author_test")
        coEvery { getRandom() } returns quote
        quoteViewModel.randomQuote()

        assert(quoteViewModel.quoteModel.value == quote)
    }

    @Test
    fun `when random quote use case return null keep the last value`() = runTest {
        val quote = Quote("test", "author_test")
        quoteViewModel.quoteModel.value = quote
        coEvery { getRandom() } returns null

        quoteViewModel.randomQuote()
        assert(quoteViewModel.quoteModel.value == quote)
    }


}
