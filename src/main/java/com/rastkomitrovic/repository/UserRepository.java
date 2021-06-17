package com.rastkomitrovic.repository;

import com.rastkomitrovic.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
