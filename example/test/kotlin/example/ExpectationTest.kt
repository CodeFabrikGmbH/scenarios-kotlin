package example

import com.github.codefabrikgmbh.scenarios.given
import example.setup.ExpectationException
import example.setup.`expection scenario`
import org.junit.Test


class ExpectationTest {
    @Test
    fun `run without expectation`() {
        given(::`expection scenario`) {
            `given the variable is set to`("other")
        }.run()
    }

    @Test
    fun `run with expectation succeeding run check`() {
        given(::`expection scenario`) {
            `given the variable is set to`("other")
            `when this throws an exception`("username.already.taken")
        }.run(ExpectationException::class) {
            `then the user gets the message`(it, "username.already.taken")
            `then the variable matches`("other")
        }
    }
}