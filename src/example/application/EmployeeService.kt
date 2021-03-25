package application

import domain.model.employee.Employee
import domain.model.employee.EmployeeRepository
import domain.model.employee.Role
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
