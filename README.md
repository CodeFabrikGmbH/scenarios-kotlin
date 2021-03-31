# Scenarios
[![build_status](https://travis-ci.org/CodeFabrikGmbH/scenarios-kotlin.svg?branch=main)](https://travis-ci.org/github/CodeFabrikGmbH/scenarios-kotlin)

This library enables a certain pattern to write readable and maintainable integration tests. It is not a framework that
integrates automatically with Spring or JavaEE.

## Example

```kotlin
@Test
fun `creating an employee should work`() {
    given(::`test organization`) {
        `when the admin creates an employee`("Test Employee", Role.EMPLOYEE)
        `then the employee should exist in the test organization`("Test Employee", Role.EMPLOYEE)
    }
} 
```

Please take a look into our example project [here](https://github.com/CodeFabrikGmbH/scenarios-kotlin/tree/main/example). We created a sample application with a complete test setup.

## Getting Started

```xml
<dependency>
    <groupId>com.github.codefabrikgmbh</groupId>
    <artifactId>scenarios-kotlin</artifactId>
    <version>1.0.1</version>
</dependency>
```

TODO: Describe setup of your tests (singleton for your application, writing scenarios, writing steps)
