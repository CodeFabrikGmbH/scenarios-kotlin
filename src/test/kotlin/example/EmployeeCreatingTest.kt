package example

import com.codefabrik.scenarios.Scenario
import example.setup.`test organization`
import com.codefabrik.scenarios.given
import example.domain.model.employee.Role
import example.setup.`then the employee should exist in the test organization`
import example.setup.`when the admin creates an employee with organization`
import example.setup.`when the admin creates an employee`
import org.junit.Test
import java.lang.RuntimeException
import java.util.*

class EmployeeCreatingTest: BasicTest() {
    @Test
    fun `creating an employee should work`() {
        given(::`test organization`) {
            `when the admin creates an employee`("Test Employee", Role.EMPLOYEE)
            `then the employee should exist in the test organization`("Test Employee", Role.EMPLOYEE)
        }
    }

    @Test
    fun `creating a supervisor should work`() {
        given(::`test organization`) {
            `when the admin creates an employee`("Test Employee", Role.SUPERVISOR)
            `then the employee should exist in the test organization`("Test Employee", Role.SUPERVISOR)
        }
    }

    @Test
    fun `creating an employee should not work if the organization does not exist`() {
        given(::Scenario, expected = RuntimeException::class) {
            `when the admin creates an employee with organization`("Test Employee", UUID.randomUUID(), Role.EMPLOYEE)
        }
    }
}
