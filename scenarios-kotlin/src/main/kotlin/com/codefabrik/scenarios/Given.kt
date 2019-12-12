package com.codefabrik.scenarios

import kotlin.reflect.KClass

fun <T : EmptyContext> given(
    context: () -> T,
    expected: KClass<*>? = null,
    afterScenario: () -> Unit = {},
    configuration: Scenario<out T>.() -> Unit
) {
    val contextResult = runCatching(context)
    contextResult.onFailure {
        afterScenario()
        throw AssertionError("Context is invalid.")
    }

    val scenarioResult = contextResult.mapCatching { instance ->
        val setup = Scenario(instance)
        configuration.invoke(setup)
    }

    afterScenario()

    if (expected != null) {
        scenarioResult.onSuccess {
            throw AssertionError("Expected error $expected has not been thrown.")
        }
        scenarioResult.onFailure {
            if (it::class == expected) null else throw it
        }
    } else {
        scenarioResult.onFailure { throw it }
    }
}
