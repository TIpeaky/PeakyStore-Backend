package com.tipeaky.peakystore.controllers;

import com.tipeaky.peakystore.model.dtos.AvaliationDTO;

import com.tipeaky.peakystore.services.AvaliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/avaliation")
public class AvaliationController {
    @Autowired
    AvaliationService avaliationService;

    @GetMapping("/{productId}")
    public ResponseEntity<List<AvaliationDTO>> getAllAvaliation(@PathVariable UUID productId ) {
        return ResponseEntity.status(HttpStatus.OK).body(avaliationService.getAllAvaliation(productId));
    }

}
