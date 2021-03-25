package example.domain.model.leave

import domain.model.employee.Employee
import java.util.*

interface LeaveRepository {
    fun save(leave: Leave): Leave
    fun list(employee: Employee): List<Leave>
    fun find(leaveId: UUID): Leave?
}
