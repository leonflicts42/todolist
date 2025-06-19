package com.br.tipraconcurso.todolist.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.br.tipraconcurso.todolist.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // JpaRepository already provides methods like findAll, findById, save, deleteById, etc.
    // You can define custom query methods here if needed.
}
