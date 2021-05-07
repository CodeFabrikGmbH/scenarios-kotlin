package example.domain.leave

import example.domain.organizationmember.OrganizationMember
import example.domain.organizationmember.Role
import java.time.LocalDate
import java.util.*

class Leave private constructor(
    val start: LocalDate,
    val end: LocalDate,
    var status: LeaveStatus,
    val organizationMemberId: UUID,
    val id: UUID = UUID.randomUUID()
) {
    companion object {
        fun create(start: LocalDate, end: LocalDate, employeeId: UUID): Leave {
            if (!end.isAfter(start)) {
                throw IllegalArgumentException("leave.error.startAfterEnd")
            }
            return Leave(start, end, LeaveStatus.PENDING, employeeId)
        }
    }

    fun accept(organizationMember: OrganizationMember) {
        if (organizationMember.role != Role.SUPERVISOR) {
            throw IllegalArgumentException("leave.error.acceptingEmployeeIsNotSupervisor")
        }
        status = LeaveStatus.ACCEPTED
    }
}

enum class LeaveStatus {
    PENDING, ACCEPTED
}
