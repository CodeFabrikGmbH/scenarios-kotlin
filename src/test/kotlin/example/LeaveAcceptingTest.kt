package example

import com.codefabrik.scenarios.given
import org.junit.Test
import example.setup.`employee with pending leave`
import example.setup.`then there should be an accepted leave for the employee`
import example.setup.`when a leave is accepted`
import java.lang.RuntimeException

class LeaveAcceptingTest: BasicTest() {
    @Test
    fun `accepting a leave as supervisor should work`() {
        given(::`employee with pending leave`) {
            `when a leave is accepted`(supervisor.id)
            `then there should be an accepted leave for the employee`()
        }
    }

    @Test
    fun `accepting a leave as an employee should result in an error`() {
        given(::`employee with pending leave`, expected = RuntimeException::class) {
            `when a leave is accepted`(employee.id)
        }
    }
}
