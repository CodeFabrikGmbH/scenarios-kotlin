package example.setup

import com.github.codefabrikgmbh.scenarios.Scenario
import example.domain.employee.Employee
import example.domain.employee.Role
import example.domain.organization.Organization
import java.time.LocalDate

open class `test organization` : Scenario() {
    val testOrganization: Organization = TestApplication.organizationService.create("Test")
}

open class `organization with employees` : `test organization`() {
    val supervisor = TestApplication.employeeService.create("supervisor", testOrganization.id, Role.SUPERVISOR)
    val employee: Employee = TestApplication.employeeService.create("employee", testOrganization.id, Role.EMPLOYEE)
}

open class `employee with pending leave` : `organization with employees`() {
    val start = LocalDate.now().plusDays(3)
    val pendingLeave = TestApplication.leaveService.request(start, start.plusDays(5), employee)
}
