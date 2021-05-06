package com.github.codefabrikgmbh.scenarios

import kotlin.reflect.KClass

fun <T : Scenario> given(
    scenarioSupplier: () -> T,
    expected: KClass<Throwable>? = null,
    afterScenario: () -> Unit = {},
    executeSteps: T.() -> Unit
) {
    given(
        scenarioSupplier,
        expected,
        afterScenario,
        {},
        executeSteps
    )
}

inline fun <T : Scenario, reified K : Throwable> given(
    scenarioSupplier: () -> T,
    expected: KClass<K>? = null,
    afterScenario: () -> Unit = {},
    verificationSteps: T.(K?) -> Unit = {},
    executeSteps: T.() -> Unit
) {
    val scenarioResult = runCatching(scenarioSupplier)
    scenarioResult.onFailure {
        afterScenario()
        throw AssertionError("Context is invalid.")
    }

    val stepsResult = scenarioResult.mapCatching(executeSteps)

    val exception = stepsResult.exceptionOrNull()
    if (exception !is K?) throw exception!!

    val verificationResult = scenarioResult.mapCatching{ verificationSteps(it, exception)}

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

    verificationResult.onFailure { throw it }
}