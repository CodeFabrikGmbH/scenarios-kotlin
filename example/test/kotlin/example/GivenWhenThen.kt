package example

import org.junit.Assert
import org.junit.Test
import kotlin.reflect.KClass

open class Scenario()

class ScenarioRunner<T : Scenario>(
    private val scenarioSupplier: () -> T,
    private val afterScenario: () -> Unit = {},
) {
    var scenarioResult: Any? = null
    var givenResult: Any? = null
    var whenResult: Any? = null
    var thenResult: Any? = null

    fun given(`given`: T.() -> Unit): ScenarioRunner<T> {
        val scenarioResult = runCatching(scenarioSupplier)
        this.scenarioResult = scenarioResult
        scenarioResult.onFailure {
            afterScenario()
            throw AssertionError("Context is invalid.")
        }

        this.givenResult = scenarioResult.mapCatching(`given`)
        scenarioResult.onFailure {
            afterScenario()
            throw it
        }
        return this
    }

    fun `when`(`when`: T.() -> Unit): ScenarioRunner<T> {
        val scenarioResult = this.scenarioResult as Result<T>

        val whenResult = scenarioResult.mapCatching{ `when`}
        val exc = whenResult.exceptionOrNull()
        this.`whenResult` = whenResult
        return this
    }

    fun `then`(`then`: T.() -> Unit): ScenarioRunner<T> {
        val scenarioResult = this.scenarioResult as Result<T>
        val whenResult = this.whenResult as Result<T>
        whenResult.exceptionOrNull() ?: throw whenResult.exceptionOrNull()!!

        val thenResult = scenarioResult.mapCatching{ then(it)}
        thenResult.onFailure { throw it }
        this.thenResult = thenResult
        return this
    }

    inline fun <reified K : Throwable> `then`(expected: KClass<K>? = null, `then`: T.(K) -> Unit): ScenarioRunner<T> {
        val scenarioResult = this.scenarioResult as Result<T>
        val whenResult = this.whenResult as Result<T>

        val exc = whenResult.exceptionOrNull()

        val exception = exc ?: throw AssertionError("Expected error $expected has not been thrown.")

        if (exception !is K) throw exception!!
        this.thenResult = scenarioResult.mapCatching{ then(it, exception)}
        return this
    }

    fun `finally`(): ScenarioRunner<T> {
        afterScenario()
        return this
    }
}


class SpecialException(message: String, val specialValue: String) : RuntimeException(message)

class `given_when_then scenario` : Scenario() {
    var variable = ""

    fun `the variable is set to`(value: String) {
        variable = value
    }

    fun `the variable matches`(value: String) {
        Assert.assertEquals(value, this.variable)
    }

    fun `this throws an exception`(value: String) {
        throw SpecialException("This is an exception!", value)
    }

    fun `then the exception matches`(e: SpecialException?, value: String) {
        Assert.assertEquals(value, e?.specialValue)
    }
}

class GivenWhenThenTest {
    @Test
    fun `verification steps are executed`() {
        ScenarioRunner(::`given_when_then scenario`)
            .`given` {
                `the variable is set to`("given")
            }.`when` {
                `the variable is set to`("when")
            }.`then` {
                `the variable matches`("when")
            }.finally()
    }

    @Test
    fun `verification steps are executed after the exception was caught`() {
        ScenarioRunner(::`given_when_then scenario`)
            .`given` {
                `the variable is set to`("given")
            }.`when` {
//                `the variable is set to`("when")
                `this throws an exception`("when exception")
            }.`then`(SpecialException::class) {
                `then the exception matches`(it, "when exception")
                `the variable matches`("when")
            }.finally()
    }
}