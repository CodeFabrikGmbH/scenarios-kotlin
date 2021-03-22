# Scenarios

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

## Getting Started

```xml
<dependency>
    <groupId>com.codefabrik.scenarios</groupId>
    <artifactId>scenarios-kotlin</artifactId>
    <version>1.0.0</version>
</dependency>
```

TODO: Describe setup of your tests (singleton for your application, writing scenarios, writing steps)
