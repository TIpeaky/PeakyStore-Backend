package com.tipeaky.peakystore.services;

import com.tipeaky.peakystore.exceptions.EntityNotFoundException;
import com.tipeaky.peakystore.model.dtos.AvaliationDTO;
import com.tipeaky.peakystore.model.dtos.ProductDTO;
import com.tipeaky.peakystore.model.entities.Avaliation;
import com.tipeaky.peakystore.model.entities.Product;
import com.tipeaky.peakystore.repositories.AvaliationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AvaliationService {

    @Autowired
    AvaliationRepository avaliationRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ModelMapper mapper;
    public List<AvaliationDTO> getAllAvaliation(UUID id) {
        ProductDTO productDTO = productService.getProduct(id);
        Product product = mapper.map(productDTO, Product.class);
        List<Avaliation> avaliationList = avaliationRepository.findAvaliationsByProductId(product);
        if (avaliationList.isEmpty()) throw new EntityNotFoundException("Esse produto ainda não possui avaliações");
        List<AvaliationDTO> avaliationDTOList = avaliationList.stream().map(avaliation -> mapper.map(avaliation, AvaliationDTO.class)).toList();
        for(int i=0; i<avaliationList.size(); i++){
            String userName = avaliationList.get(i).getUser().getName();
            avaliationDTOList.get(i).setUserName(userName);
        }
        return avaliationDTOList;

    }
}
