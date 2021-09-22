package com.julienchapron.backend.repository;

import com.julienchapron.backend.model.Geohash;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeohashRepository extends MongoRepository<Geohash, String> {

}
