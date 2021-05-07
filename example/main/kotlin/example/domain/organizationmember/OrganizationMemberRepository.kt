package example.domain.organizationmember

import java.util.*

interface OrganizationMemberRepository {
    fun save(organizationMember: OrganizationMember): OrganizationMember
    fun list(): List<OrganizationMember>
    fun find(organizationMemberId: UUID): OrganizationMember?

    /**
     * Throws an illegal argument exception if the organization member does not exist.
     */
    fun get(organizationMemberId: UUID): OrganizationMember
}
