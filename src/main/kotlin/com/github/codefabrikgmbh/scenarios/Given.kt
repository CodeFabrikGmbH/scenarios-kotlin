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
    val scenarioSupplier: () -> T,
    val afterScenario: () -> Unit,
    val executeSteps: T.() -> Unit
) {
    fun run() {
        run<Unit>(null)
    }

    inline fun <reified K : Any> run(expected: KClass<K>?, runSteps: T.(K) -> Unit = {}) {
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
            val exception = stepsResult.exceptionOrNull()
            if (exception !is K?) throw exception!!

            val verificationResult = scenarioResult.mapCatching { runSteps(it, exception!!) }

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