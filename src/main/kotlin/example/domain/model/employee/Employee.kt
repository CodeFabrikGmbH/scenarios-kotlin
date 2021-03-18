package example.domain.model.employee

import java.util.*

class Employee(
        val name: String,
        val organizationId: UUID,
        val role: Role,
        val id: UUID = UUID.randomUUID()
)

enum class Role {
    SUPERVISOR, EMPLOYEE
}
