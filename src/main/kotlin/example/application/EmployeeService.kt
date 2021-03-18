package example.application

import example.domain.model.employee.Employee
import example.domain.model.employee.EmployeeRepository
import java.util.*

class EmployeeService(
        private val employeeRepository: EmployeeRepository,
        private val organizationService: OrganizationService
) {
    fun create(
            name: String,
            organizationId: UUID
    ): Employee {
        organizationService.checkIfOrganizationExists(organizationId)
        return employeeRepository.save(Employee(name, organizationId))
    }

    fun list(): List<Employee> {
        return employeeRepository.list()
    }
}
