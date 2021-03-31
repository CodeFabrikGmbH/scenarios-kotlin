package example.domain.leave

import java.util.*

interface LeaveRepository {
    fun save(leave: Leave): Leave
    fun list(organizationMemberId: UUID): List<Leave>
    fun find(leaveId: UUID): Leave?
}
