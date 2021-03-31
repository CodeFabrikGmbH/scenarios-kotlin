package example.application

import example.domain.employee.Employee
import example.domain.employee.EmployeeRepository
import example.domain.leave.Leave
import example.domain.leave.LeaveRepository
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