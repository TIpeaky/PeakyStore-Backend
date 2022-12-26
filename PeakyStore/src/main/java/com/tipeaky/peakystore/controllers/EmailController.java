package com.tipeaky.peakystore.controllers;

import com.tipeaky.peakystore.model.dtos.EmailDTO;
import com.tipeaky.peakystore.model.forms.EmailForm;
import com.tipeaky.peakystore.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping
    public ResponseEntity<EmailDTO> sendEmail(@RequestBody @Valid EmailForm emailForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emailService.sendEmail(emailForm));
    }

}
