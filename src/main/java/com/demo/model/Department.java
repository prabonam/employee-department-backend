/**
 * 
 */
package com.demo.model;

/**
 * @author PrasadBonam
 *
 */
 

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    /*@JsonManagedReference is used on the parent side (Department → employees)*/
     @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee> employees;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}

/**
 * -- Department Table
CREATE TABLE Department (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    UNIQUE KEY unique_department_name (name)
);

-- Employee Table
CREATE TABLE Employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    designation VARCHAR(255),
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES Department(id) ON DELETE SET NULL ON UPDATE CASCADE
);
--with db name--
 CREATE TABLE test_db.Department (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    UNIQUE KEY unique_department_name (name)
);

CREATE TABLE test_db.Employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    designation VARCHAR(255),
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES Department(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Indexes for performance
CREATE INDEX idx_employee_department_id ON test_db.Employee(department_id);

 */
 