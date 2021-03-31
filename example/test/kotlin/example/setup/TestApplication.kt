package example.setup

import example.application.LeaveService
import example.application.OrganizationMemberService
import example.application.OrganizationService
import example.infrastructure.inmemory.InMemoryLeaveRepository
import example.infrastructure.inmemory.InMemoryOrganizationMemberRepository
import example.infrastructure.inmemory.InMemoryOrganizationRepository

object TestApplication {
    private val organizationRepository = InMemoryOrganizationRepository()
    private val organizationMemberRepository = InMemoryOrganizationMemberRepository()
    private val leaveRepository = InMemoryLeaveRepository()
    val organizationService: OrganizationService = OrganizationService(organizationRepository)
    val organizationMemberService: OrganizationMemberService = OrganizationMemberService(organizationMemberRepository, organizationService)
    val leaveService: LeaveService = LeaveService(leaveRepository, organizationMemberRepository)

    fun cleanUp() {
        organizationRepository.clear()
        organizationMemberRepository.clear()
        leaveRepository.clear()
    }
}
