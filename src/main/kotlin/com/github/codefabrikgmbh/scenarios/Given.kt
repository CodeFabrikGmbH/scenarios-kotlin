package com.github.codefabrikgmbh.scenarios

import kotlin.reflect.KClass

fun <T : Scenario> given(
    scenarioSupplier: () -> T,
    expected: KClass<*>? = null,
    afterScenario: () -> Unit = {},
    executeSteps: T.() -> Unit
) {
    val scenarioResult = runCatching(scenarioSupplier)
    scenarioResult.onFailure {
        afterScenario()
        throw AssertionError("Context is invalid.")
    }

    val stepsResult = scenarioResult.mapCatching(executeSteps)

    afterScenario()

    if (expected != null) {
        stepsResult.onSuccess {
            throw AssertionError("Expected error $expected has not been thrown.")
        }
        stepsResult.onFailure {
            if (it::class == expected) null else throw it
        }
    } else {
        stepsResult.onFailure { throw it }
    }
}
