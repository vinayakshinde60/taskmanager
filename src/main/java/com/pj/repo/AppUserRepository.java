package com.pj.repo;

import com.pj.model.Appuser;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface AppUserRepository extends MongoRepository<Appuser, String> {
    Appuser findByUsername(String username);
    //MongoRepository provides methods for CRUD operation
}
