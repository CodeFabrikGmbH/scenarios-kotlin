package com.github.codefabrikgmbh.scenarios

import org.junit.Assert
import org.junit.Test

class SpecialException(message: String, val specialValue: String) : RuntimeException(message)

class `test scenario` : Scenario() {
    var variable = ""

    fun `given the variable is set to`(value: String) {
        variable = value
    }

    fun `then the variable matches`(value: String) {
        Assert.assertEquals(value, this.variable)
    }

    fun `when this throws an exception`(value: String) {
        throw SpecialException("This is an exception!", value)
    }

    fun `then the user gets the message`(e: SpecialException, value: String) {
        Assert.assertEquals(value, e.specialValue)
    }
}

class GivenTest {
    @Test
    fun `run without expectation`() {
        given(::`test scenario`) {
            `given the variable is set to`("other")
        }.run()
    }

    @Test(expected = SpecialException::class)
    fun `run without expectation causes exception`() {
        given(::`test scenario`) {
            `given the variable is set to`("other")
            `when this throws an exception`("username.already.taken")
        }.run()
    }

    @Test(expected = AssertionError::class)
    fun `run with expectation without exception`() {
        given(::`test scenario`) {
            `given the variable is set to`("other")
        }.run(SpecialException::class)
    }

    @Test
    fun `run with expectation causes exception`() {
        given(::`test scenario`) {
            `given the variable is set to`("other")
            `when this throws an exception`("username.already.taken")
        }.run(SpecialException::class) {
            `then the user gets the message`(it, "username.already.taken")
        }
    }

    @Test(expected = AssertionError::class)
    fun `run with expectation causes wrong exception value`() {
        given(::`test scenario`) {
            `given the variable is set to`("other")
            `when this throws an exception`("username.already.taken")
        }.run(SpecialException::class) {
            `then the user gets the message`(it, "username.not.found")
        }
    }

    @Test(expected = SpecialException::class)
    fun `run with expectation causes wrong exception type`() {
        given(::`test scenario`) {
            `given the variable is set to`("other")
            `when this throws an exception`("username.already.taken")
        }.run(RuntimeException::class)
    }

    @Test
    fun `run with expectation succeeding run check`() {
        given(::`test scenario`) {
            `given the variable is set to`("other")
            `when this throws an exception`("username.already.taken")
        }.run(SpecialException::class) {
            `then the variable matches`("other")
        }
    }

    @Test(expected = AssertionError::class)
    fun `run with expectation and failing run check`() {
        given(::`test scenario`) {
            `given the variable is set to`("other")
            `when this throws an exception`("username.already.taken")
        }.run(SpecialException::class) {
            `then the variable matches`("this")
        }
    }
}