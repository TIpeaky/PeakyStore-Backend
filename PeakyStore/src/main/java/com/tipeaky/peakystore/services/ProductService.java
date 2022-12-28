package com.tipeaky.peakystore.services;

import com.tipeaky.peakystore.exceptions.MethodNotAllowedException;
import com.tipeaky.peakystore.filter.GenericSpecificationsBuilder;
import com.tipeaky.peakystore.filter.SpecificationFactory;
import com.tipeaky.peakystore.model.dtos.ListEnumsDTO;
import com.tipeaky.peakystore.model.dtos.EnumsDTO;
import com.tipeaky.peakystore.model.dtos.ProductDTO;
import com.tipeaky.peakystore.model.entities.Product;
import com.tipeaky.peakystore.model.enums.*;
import com.tipeaky.peakystore.model.forms.ProductUpdateForm;
import com.tipeaky.peakystore.model.forms.ProductRegisterForm;
import com.tipeaky.peakystore.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.*;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private SpecificationFactory<Product> productSpecificationFactory;

    @Autowired
    ModelMapper mapper;

    public ProductDTO getProduct(UUID id) {
        Optional<Product> product = productRepository.findByIdNotExcluded(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("Produto não encontrado");
        }

        return mapper.map(product.get(), ProductDTO.class);
    }

    public ResponseEntity<String> deleteProduct(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty() || product.get().getIsExcluded()) {
            throw new EntityNotFoundException("Produto não encontrado");
        }
        product.get().setIsExcluded(true);
        productRepository.save(product.get());

        return ResponseEntity.ok().body("Produto excluído com sucesso");
    }

    @Transactional
    public ProductDTO update(ProductUpdateForm productUpdateForm) {
        ProductDTO recoveryProductDTO = getProduct(productUpdateForm.getId());
        mapper.map(productUpdateForm, recoveryProductDTO);
        Product product = mapper.map(recoveryProductDTO, Product.class);
        product.setLastUpdateDate(LocalDateTime.now());
        product.setSku(product.generateSku());

        return mapper.map(productRepository.save(product), ProductDTO.class);
    }

    public ProductDTO save(ProductRegisterForm productRegisterForm) {
        Product product = mapper.map(productRegisterForm, Product.class);
        product.setLastUpdateDate(LocalDateTime.now());
        product.setSku(product.generateSku());

        Optional<Product> duplicatedProduct = productRepository.findBySku(product.getSku());
        if (productRepository.findBySku(product.getSku()).isPresent() && duplicatedProduct.get().getIsExcluded()) {
            product.setIsExcluded(false);
            product.setId(duplicatedProduct.get().getId());
        }
        productRepository.save(product);
        return mapper.map(product, ProductDTO.class);
    }

    public Page<ProductDTO> getAllProducts(Pageable pageable, ColorEnum color, BrandEnum productBrand, SizeEnum size, CategoryEnum category, SectionEnum section) {

        GenericSpecificationsBuilder<Product> builder = new GenericSpecificationsBuilder<>();

        if(Objects.nonNull(color)) {
            builder.with(productSpecificationFactory.isEqual("color", color));
        }

        if(Objects.nonNull(productBrand)) {
            builder.with(productSpecificationFactory.isEqual("productBrand", productBrand));
        }

        if(Objects.nonNull(size)) {
            builder.with(productSpecificationFactory.isEqual("size", size));
        }

        if(Objects.nonNull(category)) {
            builder.with(productSpecificationFactory.isEqual("category", category));
        }

        if(Objects.nonNull(section)) {
            builder.with(productSpecificationFactory.isEqual("section", section));
        }

        Page<Product> productList = productRepository.findAll(builder.build(), pageable);
        if(productList.isEmpty()) throw new EntityNotFoundException("Não há produtos cadastrados");
        return productList.map(product -> mapper.map(product, ProductDTO.class));
    }

    public ListEnumsDTO getAllEnums() {
        ListEnumsDTO teste;

        List<EnumsDTO> brandEnums = new ArrayList<>();
        List<EnumsDTO> categoryEnums = new ArrayList<>();
        List<EnumsDTO> colorEnums = new ArrayList<>();
        List<EnumsDTO> sectionEnums = new ArrayList<>();
        List<EnumsDTO> sizeEnums = new ArrayList<>();

        for(BrandEnum brandEnum : BrandEnum.values()) {
            brandEnums.add( new EnumsDTO(brandEnum.ordinal(), brandEnum.getDescription()));
        }

        for(ColorEnum colorEnum : ColorEnum.values()) {
            colorEnums.add( new EnumsDTO(colorEnum.ordinal(), colorEnum.getDescription()));
        }

        for(CategoryEnum categoryEnum : CategoryEnum.values()) {
            categoryEnums.add( new EnumsDTO(categoryEnum.ordinal(), categoryEnum.getDescription()));
        }

        for(SectionEnum sectionEnum : SectionEnum.values()) {
            sectionEnums.add( new EnumsDTO(sectionEnum.ordinal(), sectionEnum.getDescription()));
        }
        for(SizeEnum sizeEnum : SizeEnum.values()) {
            sizeEnums.add( new EnumsDTO(sizeEnum.ordinal(), sizeEnum.getDescription()));
        }

        teste = new ListEnumsDTO(brandEnums, colorEnums, sizeEnums, categoryEnums, sectionEnums);

        return teste;
    }
}

