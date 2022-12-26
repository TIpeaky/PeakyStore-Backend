package com.tipeaky.peakystore.model.entities;

import com.tipeaky.peakystore.exceptions.InvalidFormatException;
import com.tipeaky.peakystore.model.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String sku;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal purchasePrice;
    @Column(nullable = false)
    private BigDecimal salePrice;
    @Column(nullable = false)
    private Integer stockQuantity;
    @Column(nullable = false)
    private BrandEnum productBrand;
    @Column(nullable = false)
    private LocalDateTime lastUpdateDate;
    @Column(nullable = false)
    private ColorEnum color;
    @Column(nullable = false)
    private SizeEnum size;
    @Column(nullable = false)
    private CategoryEnum category;
    @Column(nullable = false)
    private SectionEnum section;

    @Column(nullable = false)
    private Boolean isExcluded = false;

    @OneToMany(mappedBy = "product")
    private Set<Avaliation> avaliations = new HashSet<>();

    public String generateSku() {
        this.sku = this.color.getKey() + this.size.getKey() + this.category.getKey() + this.section.getKey() + this.productBrand.getKey();
        return sku;
    }

    public String setSku() {
        return generateSku();
    }

    public void updateStockQuantity(int valor){
        if(valor > this.stockQuantity) {
            throw new InvalidFormatException("Quantidade indisponível do Produto");
        }
        this.stockQuantity -= valor;
    }
}