package com.example.toDoList.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.toDoList.models.Group;
import com.example.toDoList.services.GroupsService;

@RestController
@RequestMapping("/notes/groups")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupsController {

	private final GroupsService groupsService;
	
	@Autowired
	public GroupsController(GroupsService groupsService) {
		this.groupsService = groupsService;
	}
	
	@GetMapping()
	public List<Group> getGroups() {
		return groupsService.findAll();
	}
	
	@GetMapping("/{id}")
	public Group getOneGroup(@PathVariable("id") int id) {
		return groupsService.findOne(id);
	}
	
	//Добавить валидацию и обработку биндингРезалт
	@PostMapping()
	public Optional<Group> createGroup(@RequestBody Group group) {
		return Optional.of(groupsService.save(group));
	}
	
	@PatchMapping("/{id}")
	public Optional<Group> updateGroup(@RequestBody Group group) {
		return Optional.of(groupsService.save(group));
	}
	
	@DeleteMapping("/{id}")
	 public ResponseEntity<HttpStatus> deleteGroup(@PathVariable("id") int id) {
		if (groupsService.deleteGroup(id)) {
			return  ResponseEntity.ok(HttpStatus.OK);
		} else {
			//Переделать под badRequest
			return  ResponseEntity.ok(HttpStatus.NOT_FOUND);
		}
	}
}
