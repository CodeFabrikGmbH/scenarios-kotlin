package example.setup

import com.codefabrik.scenarios.Scenario
import example.TestApplication

open class `test organization` : Scenario() {
    val testOrganization = TestApplication.organizationService.create("Test")
}
