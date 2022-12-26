package com.tipeaky.peakystore.config.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true); //para ignorar atributos null
        return modelMapper;
    }
}
