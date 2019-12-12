package com.codefabrik.scenarios

import org.junit.Test

class `exception throwing context` : EmptyContext() {
    init {
        throw IllegalStateException("You created an invalided context!")
    }
}

class GivenTest {

    @Test(expected = AssertionError::class)
    fun `invalid context should result in a failed test`() {
        given(
            ::`exception throwing context`
        ) { }
    }
}

