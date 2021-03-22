package example.infrastructure.inmemory

import example.domain.model.employee.Employee
import example.domain.model.leave.Leave
import example.domain.model.leave.LeaveRepository
import java.util.*

class InMemoryLeaveRepository: LeaveRepository {
    private val leaveById: MutableMap<UUID, Leave> = mutableMapOf()

    override fun save(leave: Leave): Leave {
        leaveById[leave.id] = leave
        return leave
    }

    override fun list(employee: Employee): List<Leave> {
        return leaveById.values.filter { it.employeeId == employee.id }
    }
    fun clear() {
        leaveById.clear()
    }

}
