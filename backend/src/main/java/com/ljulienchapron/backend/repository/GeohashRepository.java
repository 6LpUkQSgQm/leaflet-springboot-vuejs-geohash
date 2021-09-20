package com.ljulienchapron.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ljulienchapron.backend.model.Geohash;

public interface GeohashRepository extends MongoRepository<Geohash, String> {
  List<Geohash> findByTitleContaining(String title);
  List<Geohash> findByPublished(boolean published);
}

