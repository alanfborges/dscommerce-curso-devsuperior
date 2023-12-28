package com.alandev.dscommerce.services;

import com.alandev.dscommerce.dto.CategoryDTO;
import com.alandev.dscommerce.dto.ProductDTO;
import com.alandev.dscommerce.dto.ProductMinDTO;
import com.alandev.dscommerce.entities.Category;
import com.alandev.dscommerce.entities.Product;
import com.alandev.dscommerce.repositories.CategoryRepository;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> result = repository.findAll();
        return result.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }
}
