package com.tipeaky.peakystore.services;

import com.tipeaky.peakystore.exceptions.InvalidFormatException;
import com.tipeaky.peakystore.message.ResponseMessage;
import com.tipeaky.peakystore.model.dtos.ProductDTO;
import com.tipeaky.peakystore.model.entities.Product;
import com.tipeaky.peakystore.model.entities.ProductImage;
import com.tipeaky.peakystore.model.forms.ProductUpdateForm;
import com.tipeaky.peakystore.model.forms.ProductRegisterForm;
import com.tipeaky.peakystore.repositories.ProductImageRepository;
import com.tipeaky.peakystore.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    private ProductImageService productImageService;


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
        MultipartFile[] files = productRegisterForm.getImages();
        List<ProductImage> ListProductImage = Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());

        Product product = mapper.map(productRegisterForm, Product.class);
        product.setImagens(ListProductImage);
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

    public ProductImage uploadFile(MultipartFile file) {
        String message = "";
        try {
            return productImageService.store(file);
        } catch (Exception e) {
            throw new InvalidFormatException("Could not upload the file: \" + file.getOriginalFilename() + \"!");
        }
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAllNotExcluded();
        if (productList.isEmpty()) throw new EntityNotFoundException("Não há produtos cadastrados");
        return productList.stream().map(product -> mapper.map(product, ProductDTO.class)).toList();
    }
}

