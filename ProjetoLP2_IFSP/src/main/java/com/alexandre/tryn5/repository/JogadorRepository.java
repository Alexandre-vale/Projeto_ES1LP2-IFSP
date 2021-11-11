package com.alexandre.tryn5.repository;

import com.alexandre.tryn5.model.Jogador;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JogadorRepository extends MongoRepository<Jogador, String> {
    
}
