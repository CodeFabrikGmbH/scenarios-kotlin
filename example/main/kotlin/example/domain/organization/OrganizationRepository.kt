package example.domain.organization

import java.util.*

interface OrganizationRepository {
    fun save(organization: Organization): Organization
    fun list(): List<Organization>
    fun find(organizationId: UUID): Organization?

    /**
     * Throws a runtime exception of the organization does not exist.
     */
    fun assertExists(organizationId: UUID)
}
