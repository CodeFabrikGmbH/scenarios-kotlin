package example.infrastructure.inmemory

import example.domain.employee.Employee
import example.domain.employee.EmployeeRepository
import java.util.*

class InMemoryEmployeeRepository : EmployeeRepository {
    private val employeeById: MutableMap<UUID, Employee> = mutableMapOf()
    override fun save(employee: Employee): Employee {
        employeeById[employee.id] = employee
        return employee
    }

    override fun list(): List<Employee> {
        return employeeById.values.toList()
    }

    override fun find(employeeId: UUID): Employee? {
        return employeeById[employeeId]
    }

    fun clear() {
        employeeById.clear()
    }
}
