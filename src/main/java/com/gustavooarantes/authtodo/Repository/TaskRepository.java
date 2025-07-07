package com.gustavooarantes.authtodo.Repository;

import com.gustavooarantes.authtodo.Model.TaskModel;
import com.gustavooarantes.authtodo.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Integer> {
    List<TaskModel> findByUser(UserModel user);
    Optional<TaskModel> findByIdAndUser(Long id, UserModel user);
}
