package example

import com.github.codefabrikgmbh.scenarios.Scenario
import com.github.codefabrikgmbh.scenarios.given
import example.domain.organizationmember.Role
import example.setup.*
import org.junit.Test
import java.util.*

class OrganizationMemberCreatingTest : BasicTest() {
    @Test
    fun `creating an employee should work`() {
        given(::`test organization`) {
            `when the admin creates an organization member`("Test Employee", Role.EMPLOYEE)
            `then an organization member should exist`("Test Employee", Role.EMPLOYEE)
        }
    }

    @Test
    fun `creating a supervisor should work`() {
        given(::`test organization`) {
            `when the admin creates an organization member`("Test Supervisor", Role.SUPERVISOR)
            `then an organization member should exist`("Test Supervisor", Role.SUPERVISOR)
        }
    }

    @Test
    fun `creating an organization member should not work if the organization does not exist`() {
        given(::Scenario, expected = AssertionError::class) {
            `when the admin creates an organization member with organization`(
                employeeName = "Test Employee",
                organizationId = UUID.randomUUID(),
                role = Role.EMPLOYEE
            )
        }
    }
}