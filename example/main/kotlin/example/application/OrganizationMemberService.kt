package example.application

import example.domain.organization.OrganizationRepository
import example.domain.organizationmember.OrganizationMember
import example.domain.organizationmember.OrganizationMemberRepository
import example.domain.organizationmember.Role
import java.util.*

class OrganizationMemberService(
    private val organizationMemberRepository: OrganizationMemberRepository,
    private val organizationRepository: OrganizationRepository,
) {
    fun create(
        name: String,
        organizationId: UUID,
        role: Role
    ): OrganizationMember {
        organizationRepository.assertExists(organizationId)
        return organizationMemberRepository.save(OrganizationMember(name, organizationId, role))
    }

    fun list(): List<OrganizationMember> {
        return organizationMemberRepository.list()
    }
}
