package com.alexandre.tryn5.repository;

import com.alexandre.tryn5.model.Mesa;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MesaRepository extends MongoRepository<Mesa, String> {
    
}
