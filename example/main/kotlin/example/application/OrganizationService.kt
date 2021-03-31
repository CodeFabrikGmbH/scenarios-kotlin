package example.application

import example.domain.organization.Organization
import example.domain.organization.OrganizationRepository
import java.util.*

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

    fun checkIfOrganizationExists(organizationId: UUID) {
        organizationRepository.find(organizationId)
            ?: throw RuntimeException("organization.error.doesNotExist")
    }
}
