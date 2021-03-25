package example

import application.EmployeeService
import application.LeaveService
import application.OrganizationService
import infrastructure.inmemory.InMemoryEmployeeRepository
import infrastructure.inmemory.InMemoryLeaveRepository
import infrastructure.inmemory.InMemoryOrganizationRepository

object TestApplication {
    private val organizationRepository = InMemoryOrganizationRepository()
    private val employeeRepository = InMemoryEmployeeRepository()
    private val leaveRepository = InMemoryLeaveRepository()
    val organizationService: OrganizationService = OrganizationService(organizationRepository)
    val employeeService: EmployeeService = EmployeeService(employeeRepository, organizationService)
    val leaveService: LeaveService = LeaveService(leaveRepository, employeeRepository)

    fun cleanUp() {
        organizationRepository.clear()
        employeeRepository.clear()
        leaveRepository.clear()
    }
}
