package example.setup

import com.codefabrik.scenarios.Scenario
import example.TestApplication

fun Scenario.`when the admin creates an organization`(organizationName: String) {
    TestApplication.organizationService.create(organizationName)
}
