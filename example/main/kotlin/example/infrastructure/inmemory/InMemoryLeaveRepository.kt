package example.infrastructure.inmemory

import example.domain.leave.Leave
import example.domain.leave.LeaveRepository
import java.util.*

class InMemoryLeaveRepository : LeaveRepository {
    private val leaveById: MutableMap<UUID, Leave> = mutableMapOf()

    override fun save(leave: Leave): Leave {
        leaveById[leave.id] = leave
        return leave
    }

    override fun list(organizationMemberId: UUID): List<Leave> {
        return leaveById.values.filter { it.organizationMemberId == organizationMemberId }
    }

    override fun find(leaveId: UUID): Leave? {
        return leaveById[leaveId]
    }

    override fun get(leaveId: UUID): Leave {
        return leaveById[leaveId] ?: throw RuntimeException("leave.error.leaveDoesNotExist")
    }

    fun clear() {
        leaveById.clear()
    }

}
