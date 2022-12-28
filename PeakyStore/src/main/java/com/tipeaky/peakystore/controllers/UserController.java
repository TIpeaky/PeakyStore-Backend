package com.tipeaky.peakystore.controllers;

import com.tipeaky.peakystore.config.security.TokenService;
import com.tipeaky.peakystore.exceptions.NullObjectException;
import com.tipeaky.peakystore.model.dtos.AddressDTO;
import com.tipeaky.peakystore.exceptions.NullObjectException;
import com.tipeaky.peakystore.model.dtos.CardDTO;
import com.tipeaky.peakystore.model.dtos.NotificationDTO;
import com.tipeaky.peakystore.model.dtos.UserDTO;
import com.tipeaky.peakystore.model.entities.User;
import com.tipeaky.peakystore.model.forms.*;
import com.tipeaky.peakystore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/client")
    public ResponseEntity<UserDTO> saveClient(@RequestBody @Valid UserForm userForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveClient(userForm));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping("/address/{userId}")
    public ResponseEntity<AddressDTO> saveAddress(@RequestBody @Valid AddressRegisterForm addressForm, @PathVariable UUID userId) {
        if(addressForm.checkNull()) throw new NullObjectException("Todos os atributos são nulos");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveAddress(addressForm, userId));
    }

    @PutMapping("/notification/{userId}")
    public ResponseEntity <NotificationDTO> updateNotification(@RequestBody @Valid NotificationForm notificationForm, @PathVariable UUID userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateNotification(notificationForm, userId));

    }

    @PostMapping("/card/{userId}")
    public ResponseEntity<CardDTO> saveCard(@RequestBody @Valid CardForm cardForm, @PathVariable UUID userId) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveCard(cardForm, userId));
    }

    @PostMapping("/newPassword")
    public ResponseEntity<String> newPassword(HttpServletRequest request, @RequestBody @Valid NewPasswordForm form) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID id = ((User)principal).getId();
        return ResponseEntity.status(HttpStatus.OK).body(userService.newPassword(id, form));
    }

    @PostMapping("/employee")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserDTO> saveEmployee(@RequestBody @Valid EmployeeForm employeeForm){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveEmployee(employeeForm));
    }

    @DeleteMapping("/employee/{userId}")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable UUID userId) {
        userService.deleteEmployee(userId);
    }

    @PutMapping("/employee/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@RequestBody @Valid EmployeeForm employeeForm, @PathVariable UUID userId) {
        userService.updateEmployee(employeeForm, userId);
    }

    @GetMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllEmployees() {
        return userService.getAllEmployees();
    }
}
