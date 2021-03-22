package example.domain.model.leave

import example.domain.model.employee.Employee
import example.domain.model.employee.Role
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import java.time.LocalDate
import java.util.*

class Leave private constructor(
        val start: LocalDate,
        val end: LocalDate,
        var status: LeaveStatus,
        val employeeId: UUID,
        val id: UUID = UUID.randomUUID()
){
    companion object {
        fun create(start: LocalDate, end: LocalDate, employeeId: UUID): Leave {
            if (!end.isAfter(start)) {
                throw IllegalArgumentException("leave.error.startAfterEnd")
            }
            return Leave(start, end, LeaveStatus.PENDING, employeeId)
        }
    }

    fun accept(employee: Employee) {
        if (employee.role != Role.SUPERVISOR) {
            throw RuntimeException("leave.error.acceptingEmployeeIsNotSupervisor")
        }
        status = LeaveStatus.ACCEPTED
    }
}

enum class LeaveStatus {
    PENDING, ACCEPTED
}
