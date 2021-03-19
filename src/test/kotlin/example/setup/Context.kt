package example.setup

import com.codefabrik.scenarios.Scenario
import example.TestApplication
import example.domain.model.employee.Employee
import example.domain.model.employee.Role
import example.domain.model.organization.Organization

open class `test organization` : Scenario() {
    val testOrganization: Organization = TestApplication.organizationService.create("Test")
}

open class `organization with employees` : `test organization`() {
    val supervisor = TestApplication.employeeService.create("supervisor", testOrganization.id, Role.SUPERVISOR)
    val employee: Employee = TestApplication.employeeService.create("employee", testOrganization.id, Role.EMPLOYEE)
}
