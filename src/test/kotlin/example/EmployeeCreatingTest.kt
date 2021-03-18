package example

import example.setup.`test organization`
import com.codefabrik.scenarios.given
import example.setup.`then the employee should exist in the test organization`
import example.setup.`when the admin creates an employee`
import org.junit.Test

class EmployeeCreatingTest {
    @Test
    fun `creating an employee should work`() {
        given(::`test organization`) {
            `when the admin creates an employee`("Test Employee")
            `then the employee should exist in the test organization`("Test Employee")
        }
    }
}
