package com.gustavooarantes.authtodo.Repository;

import com.gustavooarantes.authtodo.Model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Integer> {
}
