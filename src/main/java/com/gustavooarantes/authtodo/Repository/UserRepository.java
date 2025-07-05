package com.gustavooarantes.authtodo.Repository;

import com.gustavooarantes.authtodo.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
}
