package com.tipeaky.peakystore.controllers;

import com.tipeaky.peakystore.exceptions.NullObjectException;
import com.tipeaky.peakystore.model.dtos.ListEnumsDTO;
import com.tipeaky.peakystore.model.dtos.ProductDTO;
import com.tipeaky.peakystore.model.enums.*;
import com.tipeaky.peakystore.model.forms.ProductUpdateForm;
import com.tipeaky.peakystore.model.forms.ProductRegisterForm;
import com.tipeaky.peakystore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getAllProducts(Pageable pageable, ColorEnum color, BrandEnum productBrand, SizeEnum size, CategoryEnum category, SectionEnum section) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts(pageable, color, productBrand, size, category, section));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        return productService.deleteProduct(id);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody @Valid ProductRegisterForm productRegisterForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productRegisterForm));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable UUID id, @RequestBody @Valid ProductUpdateForm form){
        if(form.checkNull()) throw new NullObjectException("Todos os atributos são nulos");

        form.setId(id);
        return ResponseEntity.ok().body(productService.update(form));
    }

    @GetMapping("/teste")
    public ResponseEntity<ListEnumsDTO> getAllEnums() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllEnums());
    }
}
