package example

import example.application.EmployeeService
import example.application.OrganizationService
import example.infrastructure.inmemory.InMemoryEmployeeRepository
import example.infrastructure.inmemory.InMemoryOrganizationRepository

object TestApplication {
    private val organizationRepository = InMemoryOrganizationRepository()
    private val employeeRepository = InMemoryEmployeeRepository()
    val organizationService: OrganizationService = OrganizationService(organizationRepository)
    val employeeService: EmployeeService = EmployeeService(employeeRepository, organizationService)
}
