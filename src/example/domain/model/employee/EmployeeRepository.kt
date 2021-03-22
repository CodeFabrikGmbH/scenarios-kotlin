package example.domain.model.employee

import java.util.*

interface EmployeeRepository {
    fun save(employee: Employee): Employee
    fun list(): List<Employee>
    fun find(employeeId: UUID): Employee?
}
