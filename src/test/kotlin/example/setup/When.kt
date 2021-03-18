package example.setup

import com.codefabrik.scenarios.Scenario
import example.TestApplication
import example.domain.model.employee.Role
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
