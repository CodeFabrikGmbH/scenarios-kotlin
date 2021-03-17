package example.domain.model.employee

import java.util.*

class Employee(
        val name: String,
        val organizationId: UUID,
        val id: UUID = UUID.randomUUID()
)
