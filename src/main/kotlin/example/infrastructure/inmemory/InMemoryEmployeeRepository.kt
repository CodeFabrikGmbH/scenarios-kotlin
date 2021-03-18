package example.infrastructure.inmemory

import example.domain.model.employee.Employee
import example.domain.model.employee.EmployeeRepository
import java.util.*

class InMemoryEmployeeRepository: EmployeeRepository {
    private val employeeById: MutableMap<UUID, Employee> = mutableMapOf()
    override fun save(employee: Employee): Employee {
        employeeById[employee.id] = employee
        return employee
    }

    override fun list(): List<Employee> {
        return employeeById.values.toList()
    }
}
