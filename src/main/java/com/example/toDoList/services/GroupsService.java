package com.example.toDoList.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.toDoList.models.Group;
import com.example.toDoList.repositories.GroupsRepository;

@Service
@Transactional(readOnly = true)
public class GroupsService {

	private final GroupsRepository groupsRepository;
	
	@Autowired
	public GroupsService(GroupsRepository groupsRepository) {
		this.groupsRepository = groupsRepository;
	}
	
	public List<Group> findAll() {
		return groupsRepository.findAll();
	}
	
	public Group findOne(int id) {
		Optional<Group> foundGroup = groupsRepository.findById(id);
		
		//Добавить выброс исключения. Исключение создать
		return foundGroup.orElse(null);
	}
	
	@Transactional
	public Group save(Group group) {
		return groupsRepository.save(group);
	}
	
	@Transactional
	public boolean deleteGroup(int id) {
		if (isExist(id)) {
		    groupsRepository.deleteById(id);
			return true;
		} else {
			//Обработка ошибки groupNotFound
			return false;
		}
	}
	
	private boolean isExist(int id) {
		return groupsRepository.existsById(id);
	}
}
