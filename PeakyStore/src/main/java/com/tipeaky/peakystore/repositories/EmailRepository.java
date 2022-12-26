package com.tipeaky.peakystore.repositories;

import com.tipeaky.peakystore.model.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {



}
