package com.julienchapron.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.julienchapron.backend.model.GeohashObject;

public interface GeohashRepository extends MongoRepository<GeohashObject, String> {
}





