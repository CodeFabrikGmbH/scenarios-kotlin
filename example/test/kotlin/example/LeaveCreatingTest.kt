package example

import com.github.codefabrikgmbh.scenarios.given
import example.setup.*
import org.junit.Test
import java.time.LocalDate

class LeaveRequestingTest : BasicTest() {
    @Test
    fun `requesting a leave should work`() {
        given(::`organization with employees`) {
            `when the employee requests a leave`(LocalDate.now().minusDays(4), LocalDate.now(), employee)
            `then there should be a pending leave for the employee`()
        }
    }

    @Test
    fun `requesting a leave should not work if end is before start`() {
        given(::`organization with employees`, expected = IllegalArgumentException::class) {
            `when the employee requests a leave`(LocalDate.now(), LocalDate.now().minusDays(1), employee)
        }
    }
}
