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
        run<Unit>(null)
    }

    @Suppress("UNCHECKED_CAST")
    fun <K : Any> run(expected: KClass<K>?, runSteps: T.(K) -> Unit = {}) {
        val scenarioResult = runCatching(scenarioSupplier)
        scenarioResult.onFailure {
            afterScenario()
            throw AssertionError("Context is invalid.")
        }

        val stepsResult = scenarioResult.mapCatching(this.executeSteps)

        if (expected == null) {
            afterScenario()
            stepsResult.onFailure { throw it }
        } else {
            val exception = stepsResult.exceptionOrNull() ?: throw AssertionError("Expected error $expected has not been thrown.")
            val verificationResult = scenarioResult.mapCatching { runSteps(it, exception as K) }

            afterScenario()

            stepsResult.onSuccess {
                throw AssertionError("Expected error $expected has not been thrown.")
            }
            stepsResult.onFailure {
                if (it::class == expected) null else throw it
            }

            verificationResult.onFailure { throw it }
        }
    }
}