package com.leejhonatan.appfromquotes.domain

import androidx.appcompat.app.AppCompatDelegate
import io.mockk.MockKAnnotations
import io.mockk.every
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class GetDarkModeTest {

    private lateinit var getDarkMode: GetDarkMode

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getDarkMode = GetDarkMode()
    }

    @Test
    fun `check if night mode is set correctly`() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        assertEquals(AppCompatDelegate.MODE_NIGHT_YES, AppCompatDelegate.getDefaultNightMode())

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        assertEquals(AppCompatDelegate.MODE_NIGHT_NO, AppCompatDelegate.getDefaultNightMode())
    }
}