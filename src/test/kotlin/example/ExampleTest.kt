package example

import example.application.OrganizationService
import example.infrastructure.inmemory.InMemoryOrganizationRepository

open class ExampleTest {
    init {
        Application.organizationService = OrganizationService(InMemoryOrganizationRepository())
    }
}
