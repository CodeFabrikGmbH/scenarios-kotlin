package example.setup

import com.github.codefabrikgmbh.scenarios.Scenario
import example.domain.organizationmember.Role
import org.junit.Assert
import java.lang.AssertionError
import java.time.LocalDate
import java.util.*

fun Scenario.`when the admin creates an organization`(organizationName: String) {
    TestApplication.organizationService.create(organizationName)
}

fun Scenario.`when the admin creates an organization member with organization`(
    employeeName: String,
    organizationId: UUID,
    role: Role
) {
    TestApplication.organizationMemberService.create(employeeName, organizationId, role)
}

fun Scenario.`then an assertion has the message`(e: AssertionError, value: String) {
    Assert.assertEquals(value, e.message)
}


fun `test organization`.`when the admin creates an organization member`(organizationMemberName: String, role: Role) {
    TestApplication.organizationMemberService.create(organizationMemberName, testOrganization.id, role)
}

fun `organization with supervisor and employee`.`when the employee requests a leave`(start: LocalDate, end: LocalDate) {
    TestApplication.leaveService.request(employee.id, start, end)
}

fun `employee with pending leave`.`when the employee accepts the pending leave`() {
    TestApplication.leaveService.accept(employee.id, pendingLeave.id)
}

fun `employee with pending leave`.`when the supervisor accepts the pending leave`() {
    TestApplication.leaveService.accept(supervisor.id, pendingLeave.id)
}
