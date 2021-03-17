package example.domain.model.employee

interface EmployeeRepository {
    fun save(employee: Employee): Employee
}
