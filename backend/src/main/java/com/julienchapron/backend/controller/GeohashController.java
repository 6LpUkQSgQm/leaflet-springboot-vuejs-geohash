package com.julienchapron.backend.controller;

import com.julienchapron.backend.repository.GeohashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class GeohashController {

@Autowired
GeohashRepository geohashRepository;

@GetMapping("/geohashs")
public List<com.julienchapron.backend.model.Geohash> getAllGeohashs() {
    return geohashRepository.findAll();
}
}