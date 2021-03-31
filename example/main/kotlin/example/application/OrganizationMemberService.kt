package example.application

import example.domain.organizationmember.OrganizationMember
import example.domain.organizationmember.OrganizationMemberRepository
import example.domain.organizationmember.Role
import java.util.*

class OrganizationMemberService(
    private val organizationMemberRepository: OrganizationMemberRepository,
    private val organizationService: OrganizationService
) {
    fun create(
        name: String,
        organizationId: UUID,
        role: Role
    ): OrganizationMember {
        organizationService.checkIfOrganizationExists(organizationId)
        return organizationMemberRepository.save(OrganizationMember(name, organizationId, role))
    }

    fun list(): List<OrganizationMember> {
        return organizationMemberRepository.list()
    }
}
