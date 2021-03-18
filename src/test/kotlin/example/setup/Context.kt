package example.setup

import com.codefabrik.scenarios.Scenario
import example.TestApplication
import example.domain.model.organization.Organization

open class `test organization` : Scenario() {
    val testOrganization: Organization = TestApplication.organizationService.create("Test")
}
