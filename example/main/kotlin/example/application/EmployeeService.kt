package example.application

import example.domain.employee.Employee
import example.domain.employee.EmployeeRepository
import example.domain.employee.Role
import java.util.*

class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val organizationService: OrganizationService
) {
    fun create(
            name: String,
            organizationId: UUID,
            role: Role
    ): Employee {
        organizationService.checkIfOrganizationExists(organizationId)
        return employeeRepository.save(Employee(name, organizationId, role))
    }

    fun list(): List<Employee> {
        return employeeRepository.list()
    }
}
