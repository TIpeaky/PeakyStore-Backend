package com.tipeaky.peakystore.services;

import com.tipeaky.peakystore.model.entities.ProductImage;
import com.tipeaky.peakystore.repositories.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    public ProductImage store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ProductImage productImage = new ProductImage(fileName, file.getContentType(), file.getBytes());

        return productImageRepository.save(productImage);
    }

    public ProductImage getFile(String id) {
        return productImageRepository.findById(UUID.fromString(id)).get();
    }

    public Stream<ProductImage> getAllFiles() {
        return productImageRepository.findAll().stream();
    }
}
