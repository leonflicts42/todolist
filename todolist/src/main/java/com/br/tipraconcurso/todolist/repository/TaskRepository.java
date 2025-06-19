package com.br.tipraconcurso.todolist.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.br.tipraconcurso.todolist.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
