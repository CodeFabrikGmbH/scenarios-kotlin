package example

import com.codefabrik.scenarios.given
import example.setup.`organization with employees`
import example.setup.`then there should be a leave for the employee`
import example.setup.`when the employee creates a leave`
import org.junit.Test
import java.time.LocalDate

class LeaveCreatingTest : BasicTest() {
    @Test
    fun `creating a leave should work`() {
        given(::`organization with employees`) {
            `when the employee creates a leave`(LocalDate.now().minusDays(4), LocalDate.now(), employee)
            `then there should be a leave for the employee`()
        }
    }

    @Test
    fun `creating a leave should not work if end is before start`() {
        given(::`organization with employees`, expected = IllegalArgumentException::class) {
            `when the employee creates a leave`(LocalDate.now(), LocalDate.now().minusDays(1), employee)
        }
    }
}
