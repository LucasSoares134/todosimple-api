package com.Lucassoares.todosimple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Lucassoares.todosimple.models.Task;
import com.Lucassoares.todosimple.services.TaskService;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {
   @Autowired
    private TaskService taskService;

@GetMapping("/(id)")
public ResponseEntity<Task> findById(@PathVariable Long id){
    Task obj = this.taskService.findById(id);
    return ResponseEntity.ok(obj);
}

}