package example.setup

import org.junit.Before

open class BasicTest {
    @Before
    fun cleanUp() {
        TestApplication.cleanUp()
    }
}
