package example.application

import example.domain.model.employee.Employee
import example.domain.model.employee.EmployeeRepository
import example.domain.model.employee.Role
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
