package example.setup

import com.github.codefabrikgmbh.scenarios.Scenario
import example.domain.leave.LeaveStatus
import example.domain.organizationmember.Role
import org.junit.Assert

fun Scenario.`then the organization list should contain an organization`(organizationName: String) {
    val list = TestApplication.organizationService.list()
    Assert.assertEquals(1, list.size)
    Assert.assertEquals(organizationName, list[0].name)
}

fun `test organization`.`then an organization member should exist in the test organization`(organizationMemberName: String, role: Role) {
    val list = TestApplication.organizationMemberService.list()
    val organizationMember = list.find { it.name == organizationMemberName }
    Assert.assertNotNull(organizationMember)
    organizationMember?.let {
        Assert.assertEquals(organizationMemberName, it.name)
        Assert.assertEquals(testOrganization.id, it.organizationId)
        Assert.assertEquals(role, it.role)
    }
}

fun `organization with supervisor and employee`.`then the employee should have a pending leave`() {
    val list = TestApplication.leaveService.list(employee.id)
    Assert.assertEquals(1, list.size)
    Assert.assertEquals(LeaveStatus.PENDING, list[0].status)
}

fun `organization with supervisor and employee`.`then the employee should have an accepted leave`() {
    val list = TestApplication.leaveService.list(employee.id)
    Assert.assertEquals(1, list.size)
    Assert.assertEquals(LeaveStatus.ACCEPTED, list[0].status)
}
