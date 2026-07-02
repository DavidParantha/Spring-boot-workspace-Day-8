package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class MyController {

    // In-memory store for employees
    private final Map<Integer, Employee> employeeStore = new HashMap<>();

    // Create an employee (POST)
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        employeeStore.put(employee.getId(), employee);
        return employee;
    }

    // Get all employees (GET)
    @GetMapping
    public Collection<Employee> getAllEmployees() {
        return employeeStore.values();
    }

    // Get an employee by ID (GET)
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return employeeStore.get(id);
    }

    // Update an employee (PUT)
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
        if (employeeStore.containsKey(id)) {
            updatedEmployee.setId(id); // Ensure the ID matches the path variable
            employeeStore.put(id, updatedEmployee);
            return updatedEmployee;
        }
        return null; // Could also throw an exception or return a 404 ResponseEntity
    }

    // Delete an employee (DELETE)
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        if (employeeStore.containsKey(id)) {
            employeeStore.remove(id);
            return "Employee with ID " + id + " has been deleted successfully.";
        }
        return "Employee with ID " + id + " not found.";
    }
}
