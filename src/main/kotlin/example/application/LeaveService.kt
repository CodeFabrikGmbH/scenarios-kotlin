package example.application

import example.domain.model.employee.Employee
import example.domain.model.leave.Leave
import example.domain.model.leave.LeaveRepository
import java.time.LocalDate
import java.util.*

class LeaveService(
        private val leaveRepository: LeaveRepository
) {

    fun create(start: LocalDate, end: LocalDate, employee: Employee): Leave {
        return leaveRepository.save(Leave.create(start, end, employee.id))
    }

    fun list(employee: Employee): List<Leave> {
        return leaveRepository.list(employee)
    }

}
