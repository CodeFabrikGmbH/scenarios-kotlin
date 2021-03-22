package example.setup

import com.codefabrik.scenarios.Scenario
import example.TestApplication
import example.domain.model.employee.Role
import org.junit.Assert

fun Scenario.`then the organization list should contain an organization`(organizationName: String) {
    val list = TestApplication.organizationService.list()
    Assert.assertEquals(1, list.size)
    Assert.assertEquals(organizationName, list[0].name)
}

fun `test organization`.`then the employee should exist in the test organization`(employeeName: String, role: Role) {
    val list = TestApplication.employeeService.list()
    val employee = list.find { it.name == employeeName }
    Assert.assertNotNull(employee)
    employee?.let {
        Assert.assertEquals(employeeName, it.name)
        Assert.assertEquals(testOrganization.id, it.organizationId)
        Assert.assertEquals(role, it.role)
    }
}

fun `organization with employees`.`then there should be a leave for the employee`() {
    val list = TestApplication.leaveService.list(employee)
    Assert.assertEquals(1, list.size)
}