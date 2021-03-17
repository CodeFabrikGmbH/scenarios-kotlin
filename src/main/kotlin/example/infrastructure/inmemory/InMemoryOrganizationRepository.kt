package example.infrastructure.inmemory

import example.domain.model.organization.Organization
import example.domain.model.organization.OrganizationRepository
import java.util.*

class InMemoryOrganizationRepository: OrganizationRepository {
    private val organizationById: MutableMap<UUID, Organization> = mutableMapOf()
    override fun save(organization: Organization): Organization {
        organizationById[organization.id] = organization
        return organization
    }

    override fun list(): List<Organization> {
        return organizationById.values.toList();
    }

    override fun find(organizationId: UUID): Organization? {
        return organizationById[organizationId]
    }
}
