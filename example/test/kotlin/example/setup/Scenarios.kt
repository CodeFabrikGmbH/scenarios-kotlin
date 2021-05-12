package example.setup

import com.github.codefabrikgmbh.scenarios.Scenario
import example.domain.organization.Organization
import example.domain.organizationmember.OrganizationMember
import example.domain.organizationmember.Role
import org.junit.Assert
import java.time.LocalDate

open class `test organization` : Scenario() {
    val testOrganization: Organization = TestApplication.organizationService.create("Test")
}

open class `organization with supervisor and employee` : `test organization`() {
    val supervisor =
        TestApplication.organizationMemberService.create("supervisor", testOrganization.id, Role.SUPERVISOR)
    val employee: OrganizationMember =
        TestApplication.organizationMemberService.create("employee", testOrganization.id, Role.EMPLOYEE)
}

open class `employee with pending leave` : `organization with supervisor and employee`() {
    val start = LocalDate.now().plusDays(3)
    val pendingLeave = TestApplication.leaveService.request(employee.id, start, start.plusDays(5))
}


class ExpectationException(message: String, val specialValue: String) : RuntimeException(message)

open class `expection scenario` : Scenario() {
    var variable = ""

    fun `given the variable is set to`(value: String) {
        variable = value
    }

    fun `then the variable matches`(value: String) {
        Assert.assertEquals(value, this.variable)
    }

    fun `when this throws an exception`(value: String) {
        throw ExpectationException("This is an exception!", value)
    }

    fun `then the user gets the message`(e: ExpectationException, value: String) {
        Assert.assertEquals(value, e.specialValue)
    }
}