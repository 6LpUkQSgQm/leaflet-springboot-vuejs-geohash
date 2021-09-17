package com.jeonguk.vuejs.repository;

import com.jeonguk.vuejs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastName(@Param("last_name") String lastname);
    List<User> findByFirstName(@Param("first_name") String firstname);
}
