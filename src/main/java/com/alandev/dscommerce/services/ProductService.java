package com.alandev.dscommerce.services;

import com.alandev.dscommerce.dto.ProductDTO;
import com.alandev.dscommerce.entities.Product;
import com.alandev.dscommerce.repositories.ProductRepository;
import com.alandev.dscommerce.services.exceptions.DatabaseException;
import com.alandev.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable);
        return result.map(product -> new ProductDTO(product));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        // instancia e copia os dados do dto para entidade
        Product entity = new Product();
        copyDtoToEntity(dto, entity);

        //salva no banco
        entity = repository.save(entity);
        //converte em dto novamente retornando o obj salvo e atualizado
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            // instancia um produto com a referencia do id que eu passa como argumento
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);

            //salva no banco
            entity = repository.save(entity);
            //converte em dto novamente retornando o obj salvo e atualizado
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }
}
