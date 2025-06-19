package com.br.tipraconcurso.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


import com.br.tipraconcurso.todolist.model.Task;
import com.br.tipraconcurso.todolist.repository.TaskRepository;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/todolist/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @GetMapping("/id")
    public ResponseEntity<Task> getTaskById(@RequestParam Long id){
        Optional<Task> task = taskRepository.findById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); 
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("/id")
    public ResponseEntity<Task> updadeTask(@PathVariable Long id, @RequestBody Task taskDetail){
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            Task existingTask = task.get();
            existingTask.setTitle(taskDetail.getTitle());
            existingTask.setDescription(taskDetail.getDescription());
            existingTask.setCompleted(taskDetail.getCompleted());

            Task updatedTask = taskRepository.save(existingTask);
            return ResponseEntity.ok(updatedTask);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            taskRepository.delete(task.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
