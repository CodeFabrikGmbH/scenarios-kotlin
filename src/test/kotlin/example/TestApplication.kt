package example

import example.application.EmployeeService
import example.application.LeaveService
import example.application.OrganizationService
import example.infrastructure.inmemory.InMemoryEmployeeRepository
import example.infrastructure.inmemory.InMemoryLeaveRepository
import example.infrastructure.inmemory.InMemoryOrganizationRepository

object TestApplication {
    private val organizationRepository = InMemoryOrganizationRepository()
    private val employeeRepository = InMemoryEmployeeRepository()
    private val leaveRepository = InMemoryLeaveRepository()
    val organizationService: OrganizationService = OrganizationService(organizationRepository)
    val employeeService: EmployeeService = EmployeeService(employeeRepository, organizationService)
    val leaveService: LeaveService = LeaveService(leaveRepository)

    fun cleanUp() {
        organizationRepository.clear()
        employeeRepository.clear()
        leaveRepository.clear()
    }
}
