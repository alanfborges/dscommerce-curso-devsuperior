package com.alandev.dscommerce.services;

import com.alandev.dscommerce.dto.ProductDTO;
import com.alandev.dscommerce.entities.Product;
import com.alandev.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        //busca um produto no banco de dados
        Optional<Product> result = productRepository.findById(id);
        //pega o produto dentro do optional
        Product product = result.get();
        //copia os dados do produyo obtido para um dto
        ProductDTO dto = new ProductDTO(product);
        //retorna o dto
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(product -> new ProductDTO(product));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        // instancia e copia os dados do dto para entidade
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

        //salva no banco
        entity = productRepository.save(entity);
        //converte em dto novamente retornando o obj salvo e atualizado
        return new ProductDTO(entity);
    }
}
