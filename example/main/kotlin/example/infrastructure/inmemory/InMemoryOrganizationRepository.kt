package example.infrastructure.inmemory

import example.domain.organization.Organization
import example.domain.organization.OrganizationRepository
import java.util.*

class InMemoryOrganizationRepository : OrganizationRepository {
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

    override fun assertExists(organizationId: UUID) {
        organizationById[organizationId] ?: throw AssertionError("organization.error.doesNotExist")
    }

    fun clear() {
        organizationById.clear()
    }
}
