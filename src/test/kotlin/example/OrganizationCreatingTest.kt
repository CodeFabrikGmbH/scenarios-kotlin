package example

import com.codefabrik.scenarios.Scenario
import com.codefabrik.scenarios.given
import org.junit.Assert
import org.junit.Test


class OrganizationCreatingTest {
    @Test
    fun `creating an organization should work`() {
        given(::Scenario) {
            `when the admin creates an organization`("Test")
            `then the organization list should contain an organization`("Test")
        }
    }
}

private fun Scenario.`when the admin creates an organization`(organizationName: String) {
    TestApplication.organizationService.create(organizationName)
}

private fun Scenario.`then the organization list should contain an organization`(organizationName: String) {
    val list = TestApplication.organizationService.list()
    Assert.assertEquals(1, list.size)
    Assert.assertEquals(organizationName, list[0].name)
}
