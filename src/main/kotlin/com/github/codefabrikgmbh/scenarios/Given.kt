package com.github.codefabrikgmbh.scenarios

import kotlin.reflect.KClass

fun <T : Scenario> given(
    scenarioSupplier: () -> T,
    afterScenario: () -> Unit = {},
    executeSteps: T.() -> Unit
): ScenarioRunner<T> {
    return ScenarioRunner(scenarioSupplier, afterScenario, executeSteps)
}

class ScenarioRunner<T : Scenario>(
    private val scenarioSupplier: () -> T,
    private val afterScenario: () -> Unit,
    private val executeSteps: T.() -> Unit
) {
    fun run() {
        runExpecting<Unit>(null)
    }

    @Suppress("UNCHECKED_CAST")
    fun <K : Any> runExpecting(expected: KClass<K>?, expectationSteps: T.(K) -> Unit = {}) {
        val scenarioResult = runCatching(scenarioSupplier)
        scenarioResult.onFailure {
            afterScenario()
            throw AssertionError("Context is invalid.")
        }

        val executionResult = scenarioResult.mapCatching(this.executeSteps)

        if (expected == null) {
            afterScenario()
            executionResult.onFailure { throw it }
        } else {
            val exception = executionResult.exceptionOrNull() ?: throw AssertionError("Expected error $expected has not been thrown.")
            val expectationResult = scenarioResult.mapCatching { expectationSteps(it, exception as K) }

            afterScenario()

            executionResult.onSuccess {
                throw AssertionError("Expected error $expected has not been thrown.")
            }
            executionResult.onFailure {
                if (it::class != expected) throw it
            }

            expectationResult.onFailure { throw it }
        }
    }
}