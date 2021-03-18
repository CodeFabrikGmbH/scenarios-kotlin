package example

import org.junit.After
import org.junit.Before

open class BasicTest {
    @Before
    fun cleanUp() {
        TestApplication.cleanUp()
    }
}
