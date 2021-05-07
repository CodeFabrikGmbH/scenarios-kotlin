package example

import com.github.codefabrikgmbh.scenarios.given
import example.setup.*
import org.junit.Test

class LeaveAcceptingTest : BasicTest() {
    @Test
    fun `accepting a leave as supervisor should work`() {
        given(::`employee with pending leave`) {
            `when the supervisor accepts the pending leave`()
            `then the employee should have an accepted leave`()
        }.run()
    }

    @Test
    fun `accepting a leave as an employee should result in an error`() {
        given(::`employee with pending leave`) {
            `when the employee accepts the pending leave`()
        }.run(IllegalArgumentException::class)
    }
}
