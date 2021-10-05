package com.julienchapron.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.julienchapron.backend.model.Geohash;

public interface GeohashRepository extends MongoRepository<Geohash, String> {
}





