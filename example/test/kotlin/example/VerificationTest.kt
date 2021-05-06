package example

import com.github.codefabrikgmbh.scenarios.Scenario
import com.github.codefabrikgmbh.scenarios.given
import org.junit.Assert
import org.junit.Test

class MyException(message: String, val specialValue: String) : RuntimeException(message)

class `verification scenario` : Scenario() {
    fun `this throws an exception`(value: String) {
        throw MyException("This is an exception!", value)
    }

    fun `then the exception matches this special value`(e: MyException?, value: String) {
        Assert.assertEquals(value, e?.specialValue)
    }
}

class VerificationTest {

    @Test
    fun `checking for exceptions without further verifications`() {
        given(::`verification scenario`, expected = MyException::class) {
            `this throws an exception`("this went wrong")
        }
    }

    @Test
    fun `verification steps are executed after the exception was caught`() {
        given(::`verification scenario`,
            expected = MyException::class,
            `when` = {
                `this throws an exception`("this went wrong")
            },
            then = {
                `then the exception matches this special value`(it, "this went wrong")
            }
        )
    }
}