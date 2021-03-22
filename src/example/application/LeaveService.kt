package example.application

import example.domain.model.employee.Employee
import example.domain.model.employee.EmployeeRepository
import example.domain.model.leave.Leave
import example.domain.model.leave.LeaveRepository
import java.lang.RuntimeException
import java.time.LocalDate
import java.util.*

class LeaveService(
        private val leaveRepository: LeaveRepository,
        private val employeeRepository: EmployeeRepository
) {

    fun request(start: LocalDate, end: LocalDate, employee: Employee): Leave {
        return leaveRepository.save(Leave.create(start, end, employee.id))
    }

    fun list(employee: Employee): List<Leave> {
        return leaveRepository.list(employee)
    }

    fun accept(leaveId: UUID, acceptorId: UUID): Leave {
        val leave = leaveRepository.find(leaveId) ?: throw RuntimeException("leave.error.leaveDoesNotExist")
        val acceptor = employeeRepository.find(acceptorId) ?: throw RuntimeException("leave.error.acceptorDoesNotExist")
        leave.accept(acceptor)
        return leave
    }

}
