package example.setup

import com.github.codefabrikgmbh.scenarios.Scenario
import example.domain.employee.Employee
import example.domain.employee.Role
import java.time.LocalDate
import java.util.*

fun Scenario.`when the admin creates an organization`(organizationName: String) {
    TestApplication.organizationService.create(organizationName)
}

fun Scenario.`when the admin creates an employee with organization`(employeeName: String, organizationId: UUID, role: Role) {
    TestApplication.employeeService.create(employeeName, organizationId, role)
}

fun `test organization`.`when the admin creates an employee`(employeeName: String, role: Role) {
    TestApplication.employeeService.create(employeeName, testOrganization.id, role)
}

fun `organization with employees`.`when the employee requests a leave`(start: LocalDate, end: LocalDate, employee: Employee) {
    TestApplication.leaveService.request(start, end, employee)
}

fun `employee with pending leave`.`when a leave is accepted`(acceptorId: UUID) {
    TestApplication.leaveService.accept(pendingLeave.id, acceptorId)
}