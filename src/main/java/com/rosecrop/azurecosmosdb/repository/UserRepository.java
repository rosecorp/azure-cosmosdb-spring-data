package com.rosecrop.azurecosmosdb.repository;

import com.microsoft.azure.spring.data.documentdb.repository.DocumentDbRepository;
import com.rosecrop.azurecosmosdb.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends DocumentDbRepository<User, String> {
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
}
