package example.infrastructure.inmemory

import example.domain.organizationmember.OrganizationMember
import example.domain.organizationmember.OrganizationMemberRepository
import java.util.*

class InMemoryOrganizationMemberRepository : OrganizationMemberRepository {
    private val organizationMemberById: MutableMap<UUID, OrganizationMember> = mutableMapOf()

    override fun save(organizationMember: OrganizationMember): OrganizationMember {
        organizationMemberById[organizationMember.id] = organizationMember
        return organizationMember
    }

    override fun list(): List<OrganizationMember> {
        return organizationMemberById.values.toList()
    }

    override fun find(organizationMemberId: UUID): OrganizationMember? {
        return organizationMemberById[organizationMemberId]
    }

    override fun get(organizationMemberId: UUID): OrganizationMember {
        return organizationMemberById[organizationMemberId]
            ?: throw RuntimeException("leave.error.acceptorDoesNotExist")
    }

    fun clear() {
        organizationMemberById.clear()
    }
}
