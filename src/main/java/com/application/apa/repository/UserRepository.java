package com.application.apa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.apa.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);

}
