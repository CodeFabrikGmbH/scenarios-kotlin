package example.application

import example.domain.model.organization.Organization
import example.domain.model.organization.OrganizationRepository

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
