package com.tipeaky.peakystore.repositories;

import com.tipeaky.peakystore.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
    @Query("SELECT p FROM Product p WHERE p.id = ?1 AND p.isExcluded = FALSE")
    Optional<Product> findByIdNotExcluded(UUID uuid);

    @Query("SELECT p FROM Product p WHERE p.isExcluded = FALSE")
    List<Product> findAllNotExcluded();

    Optional<Product> findBySku(String sku);
}
