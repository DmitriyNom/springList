package com.example.toDoList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.toDoList.models.Group;

@Repository
public interface GroupsRepository extends JpaRepository<Group, Integer>{

}
