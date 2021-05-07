package example.application

import example.domain.organization.Organization
import example.domain.organization.OrganizationRepository

class OrganizationService(
    private val organizationRepository: OrganizationRepository,
) {
    fun create(
        name: String,
    ): Organization {
        val organization = Organization(name);
        return organizationRepository.save(organization)
    }

    fun list(): List<Organization> {
        return organizationRepository.list()
    }
}
