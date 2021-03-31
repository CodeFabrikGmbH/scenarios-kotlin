package example.domain.organizationmember

import java.util.*

class OrganizationMember(
    val name: String,
    val organizationId: UUID,
    val role: Role,
    val id: UUID = UUID.randomUUID()
)

enum class Role {
    SUPERVISOR, EMPLOYEE
}
