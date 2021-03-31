package example

import com.github.codefabrikgmbh.scenarios.Scenario
import com.github.codefabrikgmbh.scenarios.given
import example.setup.BasicTest
import example.setup.`then the organization list should contain an organization`
import example.setup.`when the admin creates an organization`
import org.junit.Test

class OrganizationCreatingTest : BasicTest() {
    @Test
    fun `creating an organization should work`() {
        given(::Scenario) {
            `when the admin creates an organization`("Test")
            `then the organization list should contain an organization`("Test")
        }
    }
}
