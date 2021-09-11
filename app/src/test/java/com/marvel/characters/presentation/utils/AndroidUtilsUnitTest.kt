package com.marvel.characters.presentation.utils

import org.junit.Assert
import org.junit.Test

class AndroidUtilsUnitTest {

    @Test
    fun `Get current time stamp, when is get the time stamp, then returns a string time stamp`() {
        val actualTs = getCurrentTimeStamp()
        Assert.assertTrue(actualTs.isNotEmpty())
    }

    @Test
    fun `Get current time stamp, when is get the time stamp, then returns if is empty`() {
        val actualTs = getCurrentTimeStamp()
        Assert.assertFalse(actualTs.isEmpty())
    }
}