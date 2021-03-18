package example.setup

import com.codefabrik.scenarios.Scenario
import example.TestApplication
import example.domain.model.employee.Role

fun Scenario.`when the admin creates an organization`(organizationName: String) {
    TestApplication.organizationService.create(organizationName)
}

fun `test organization`.`when the admin creates an employee`(employeeName: String, role: Role) {
    TestApplication.employeeService.create(employeeName, testOrganization.id, role)
}
