package example.application

import example.domain.leave.Leave
import example.domain.leave.LeaveRepository
import example.domain.organizationmember.OrganizationMemberRepository
import java.time.LocalDate
import java.util.*

class LeaveService(
    private val leaveRepository: LeaveRepository,
    private val organizationMemberRepository: OrganizationMemberRepository
) {

    fun request(organizationMemberId: UUID, start: LocalDate, end: LocalDate): Leave {
        return leaveRepository.save(Leave.create(start, end, organizationMemberId))
    }

    fun list(organizationMemberId: UUID): List<Leave> {
        return leaveRepository.list(organizationMemberId)
    }

    fun accept(acceptorId: UUID, leaveId: UUID): Leave {
        val leave = leaveRepository.get(leaveId)
        val acceptor = organizationMemberRepository.get(acceptorId)
        leave.accept(acceptor)
        return leave
    }

}
