/**
 * 
 */
package com.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author PrasadBonam
 *
 */
import jakarta.persistence.*;
import lombok.*;

//Employee.java

@Entity
@Data // This includes getter, setter, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

	/*
	 * public Employee(Long id, String name, String email, String designation,
	 * Department department) { super(); this.id = id; this.name = name; this.email
	 * = email; this.designation = designation; this.department = department; }
	 * public Employee() {
	 * 
	 * }
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String email;
	private String designation;

	@ManyToOne
	@JoinColumn(name = "department_id")
	@JsonBackReference
	private Department department;

 

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
