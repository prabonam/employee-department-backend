package com.demo.controller;

import com.demo.model.Employee;
import com.demo.service.EmployeeService;
import com.demo.exception.EmployeeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 *  *  * 
 * @author PrasadBonam
 * 
 * Spring Boot Version     Compatible Spring Framework  Java Version Required
=========================================================================
2.7.x					5.x 							Java 8 to 17

3.x (including 3.2.3) 	6.x 							Java 17 or higher
 *
 *===========================================================================
 *Spring Boot 3.2.3 does not support Spring Framework 5.x.
 *
 Spring Boot 3.x is built exclusively on top of Spring Framework 6.x, which comes with major architectural changes.
 
 *
 --- Major Changes in Spring Framework 6 / Spring Boot 3.x: ----

Jakarta EE 10: All javax.*  packages have moved to jakarta.*. This is a breaking change.

Java 17 baseline: Java 17 is the minimum supported version.

Native support: Enhanced support for GraalVM native images.

---- If You Want to Use Spring Framework 5.x: ------

You must stick with Spring Boot 2.7.x or earlier.

Let me know if you’d like:

Help upgrading your application to Spring Boot 3.x

Migration guide from javax.* to jakarta.*

Example project using Spring Boot 3.2.3 with Java 17+
 */

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable   Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> createEmployee(  @RequestBody Employee employee) {
        Employee created = employeeService.createEmployee(employee);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                    @RequestBody Employee employee) {
        Employee updated = employeeService.updateEmployee(id, employee);
        if (updated == null) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable  Long id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (!deleted) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        return ResponseEntity.noContent().build();
    }
}


/**
 * Base URL: http://localhost:8080/api/employees

1. Get All Employees

Method: GET
URL: /getEmployees

Example:

GET http://localhost:8080/api/employees/getEmployees

Response:

[
  {
    "id": 1,
    "name": "John",
    "departmentId": 101
  },
  {
    "id": 2,
    "name": "Jane",
    "departmentId": 102
  }
]

2. Get Employee by ID

Method: GET
URL: /{id}

Example:

GET http://localhost:8080/api/employees/1

Response:

{
  "id": 1,
  "name": "John",
  "departmentId": 101
}

3. Add New Employee

Method: POST
URL: /addEmployee
http://localhost:8080/api/employees/addEmployee
Headers:

Content-Type: application/json

Body:

{
  "name": "Alice",
  "departmentId": 103
}

Response:

{
  "id": 3,
  "name": "Alice",
  "departmentId": 103
}

4. Update Existing Employee

Method: PUT
URL: /{id}

Example:

PUT http://localhost:8080/api/employees/1

Headers:

Content-Type: application/json

Body:

{
  "name": "John Updated",
  "departmentId": 105
}

Response:

{
  "id": 1,
  "name": "John Updated",
  "departmentId": 105
}

5. Delete Employee

Method: DELETE
URL: /{id}

Example:

DELETE http://localhost:8080/api/employees/1

Response: 204 No Content
 */
 /* =========================== */

/*
Employee APIs
1. Get All Employees
 
GET /api/employees/getEmployees
2. Get Employee by ID
 
GET /api/employees/{id}
Example:

 
GET /api/employees/1
3. Add Employee
 
POST /api/employees/addEmployee
Body:

 
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "designation": "Developer",
  "department": {
    "id": 2
  }
}
🔸 Make sure department with ID 2 exists before testing.

4. Update Employee
 
PUT /api/employees/{id}
Example:
 
PUT /api/employees/1
Body:

 
{
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "designation": "Lead Developer",
  "department": {
    "id": 2
  }
}
5. Delete Employee
 
DELETE /api/employees/{id}
Example:

 
DELETE /api/employees/1

*/