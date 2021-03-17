package example.setup

import com.codefabrik.scenarios.Scenario
import example.TestApplication
import org.junit.Assert

fun Scenario.`then the organization list should contain an organization`(organizationName: String) {
    val list = TestApplication.organizationService.list()
    Assert.assertEquals(1, list.size)
    Assert.assertEquals(organizationName, list[0].name)
}
