package example

import example.application.OrganizationService
import example.infrastructure.inmemory.InMemoryOrganizationRepository

object TestApplication {
    private val organizationRepository = InMemoryOrganizationRepository()
    val organizationService: OrganizationService = OrganizationService(organizationRepository)
}
