package example.setup

import com.codefabrik.scenarios.Scenario
import example.TestApplication
import example.domain.model.employee.Employee
import example.domain.model.employee.Role
import example.domain.model.organization.Organization
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
