package example.domain.model.leave

import example.domain.model.employee.Employee

interface LeaveRepository {
    fun save(leave: Leave): Leave
    fun list(employee: Employee): List<Leave>
}
