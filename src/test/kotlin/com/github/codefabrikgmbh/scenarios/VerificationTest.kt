package com.github.codefabrikgmbh.scenarios

import org.junit.Test

class `verification scenario` : Scenario() {
    fun `this throws an exception`() {
        throw IllegalStateException("This is an exception!")
    }

    fun `this is processed anyway`() {
        println("This is processed anyway")
    }

}

class VerificationTest {
    @Test
    fun `checking for exceptions without further verifications`() {
        given(::`verification scenario`, expected = IllegalStateException::class) {
            `this throws an exception`()
        }
    }

    @Test
    fun `verification steps are executed after the exception was caught`() {
        given(::`verification scenario`,
            expected = IllegalStateException::class,
            executeSteps = {
                `this throws an exception`()
            },
            verificationSteps = {
                `this is processed anyway`()
            }
        )
    }
}