package com.tipeaky.peakystore.repositories;
import com.tipeaky.peakystore.model.entities.Avaliation;
import com.tipeaky.peakystore.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AvaliationRepository extends JpaRepository<Avaliation, UUID> {

    @Query("SELECT a FROM Avaliation a WHERE a.product = ?1")
    List<Avaliation> findAvaliationsByProductId(Product product);




}
