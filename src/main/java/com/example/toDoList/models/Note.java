package com.example.toDoList.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="note_list")
public class Note {
	
	//Fields-----------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @NotNull
//    @AssertTrue or
//    @AssertFalse
    private boolean status;
    
    @Column(name="expiration_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date expirationDate;
    
    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Column(name="description")
    @Size(max = 500, message = "Maximum length of the description is 500 characters")
    private String description;
    
    @Column(name = "priority")
    @Min(value = 1, message = "priority should be greater than 1")    
    @Max(value = 10, message = "priority should be lower than 10")
    private int priority;
    
    
    //Constructors-----------------------------------------------------------------------

    public Note() {

    }
    
    public Note(String name, boolean status, int priority) {
        this.name = name;
        this.status = status;
        this.priority = priority;
    }
    
    //Accessors--------------------------------------------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public void setExpirationDate(Date expirationDate) {
    	this.expirationDate = expirationDate;
    }
    
    public Date getExpirationDate() {
    	return expirationDate;
    }
    
    public void setCreatedAt(Date createdAt) {
    	this.createdAt = createdAt;
    }
    
    public Date getCreatedAt() {
    	return createdAt;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public String getDescription() {
    	return this.description;
    }
    
    public void setPriority(int priority) {
    	this.priority = priority;
    }
    
    public int getPriority() {
    	return this.priority;
    }
 
}
