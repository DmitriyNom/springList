package com.example.toDoList.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="group_list")
public class Group {
	
	//Fields-----------------------------------------------------------------------------

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
	private String name;
	
	
	//Constructors-----------------------------------------------------------------------
	
	public Group() {}
	
	public Group(String name) {
		this.name = name;
	}
	
	//Accessors--------------------------------------------------------------------------
	
	public int getId() {
		return this.id;
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
