package example.domain.organization

import java.util.*

class Organization(
    val name: String,
    val id: UUID = UUID.randomUUID()
)
