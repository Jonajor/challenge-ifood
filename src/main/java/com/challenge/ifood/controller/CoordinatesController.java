package com.challenge.ifood.controller;

import com.challenge.ifood.model.Wather;
import com.challenge.ifood.service.CoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/coordinates")
public class CoordinatesController {

    @Autowired
    private CoordinatesService coordinatesService;

    @GetMapping
    private ResponseEntity<Wather> getCoordinate(@RequestParam(required = false) Optional<Double> latitude,
                                                 @RequestParam(required = false) Optional<Double> longitude,
                                                 @RequestParam(required = false) Optional<String> cidade){
        var teste = coordinatesService.getCoordinates(latitude, longitude, cidade);

        return ResponseEntity.ok().body(teste);
    }
}
