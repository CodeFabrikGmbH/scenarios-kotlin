package example

import com.github.codefabrikgmbh.scenarios.given
import example.setup.BasicTest
import example.setup.`organization with supervisor and employee`
import example.setup.`then the employee should have a pending leave`
import example.setup.`when the employee requests a leave`
import org.junit.Test
import java.time.LocalDate

class LeaveRequestingTest : BasicTest() {
    @Test
    fun `requesting a leave should create pending leave`() {
        given(::`organization with supervisor and employee`) {
            `when the employee requests a leave`(LocalDate.now().minusDays(4), LocalDate.now())
            `then the employee should have a pending leave`()
        }.run()
    }

    @Test
    fun `requesting a leave should not work if end is before start`() {
        given(::`organization with supervisor and employee`) {
            `when the employee requests a leave`(LocalDate.now(), LocalDate.now().minusDays(1))
        }.runExpecting(IllegalArgumentException::class)
    }
}
