package example.domain.leave

import example.domain.employee.Employee
import java.util.*

interface LeaveRepository {
    fun save(leave: Leave): Leave
    fun list(employee: Employee): List<Leave>
    fun find(leaveId: UUID): Leave?
}
