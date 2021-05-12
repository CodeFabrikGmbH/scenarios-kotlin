package com.github.codefabrikgmbh.scenarios

import org.junit.Assert
import org.junit.Test

class ExpectationException(message: String, val specialValue: String) : RuntimeException(message)

class `exception throwing scenario` : Scenario() {
    init {
        throw IllegalStateException("You created an invalided context!")
    }
}

class `variable scenario` : Scenario() {
    var variable = ""

    fun `when the variable is set to`(value: String) {
        variable = value
    }

    fun `then the variable matches`(value: String) {
        Assert.assertEquals(value, this.variable)
    }

    fun `when this throws an exception`(value: String = "") {
        throw ExpectationException("This is an exception!", value)
    }

    fun `then an exception has the message`(e: ExpectationException, value: String) {
        Assert.assertEquals(value, e.specialValue)
    }
}

class GivenTest {
    @Test(expected = AssertionError::class)
    fun `invalid context should result in a failed test`() {
        given(::`exception throwing scenario`){
        }.run()
    }

    @Test(expected = ExpectationException::class)
    fun `throwing an exception should result in a failed test`() {
        given(::`variable scenario`) {
            `when this throws an exception`()
        }.run()
    }

    @Test(expected = AssertionError::class)
    fun `expecting an exception and none is thrown should result in a failed test`() {
        given(::`variable scenario`) {
        }.runExpecting(ExpectationException::class)
    }

    @Test
    fun `expecting an exception should work`() {
        given(::`variable scenario`) {
            `when this throws an exception`("username.already.taken")
        }.runExpecting(ExpectationException::class) {
            `then an exception has the message`(it, "username.already.taken")
        }
    }

    @Test(expected = AssertionError::class)
    fun `expecting an exception with a different value should result in a failed test`() {
        given(::`variable scenario`) {
            `when this throws an exception`("username.already.taken")
        }.runExpecting(ExpectationException::class) {
            `then an exception has the message`(it, "username.not.found")
        }
    }

    @Test(expected = ExpectationException::class)
    fun `expecting an exception with a different type should result in a failed test`() {
        given(::`variable scenario`) {
            `when this throws an exception`()
        }.runExpecting(RuntimeException::class)
    }

    @Test
    fun `asserting a valid value after expecting an exception should work`() {
        given(::`variable scenario`) {
            `when the variable is set to`("other")
            `when this throws an exception`()
        }.runExpecting(ExpectationException::class) {
            `then the variable matches`("other")
        }
    }

    @Test(expected = AssertionError::class)
    fun `asserting an invalid value after expecting an exception should result in a failed test`() {
        given(::`variable scenario`) {
            `when the variable is set to`("other")
            `when this throws an exception`()
        }.runExpecting(ExpectationException::class) {
            `then the variable matches`("this")
        }
    }
}