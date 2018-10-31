package com.springboot100steps.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot100steps.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
