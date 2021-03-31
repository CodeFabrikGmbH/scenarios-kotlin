package example.domain.organization

import java.util.*

interface OrganizationRepository {
    fun save(organization: Organization): Organization
    fun list(): List<Organization>
    fun find(organizationId: UUID): Organization?
}