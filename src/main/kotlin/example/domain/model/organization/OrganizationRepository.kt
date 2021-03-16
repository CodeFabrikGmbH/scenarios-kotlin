package example.domain.model.organization

interface OrganizationRepository {
    fun save(organization: Organization): Organization
    fun list(): List<Organization>
}
