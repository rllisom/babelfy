package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService {


    private final CategoryRepository repo;

    public CategoryDTO showCategory(Long id){
        Category c = repo.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se encuentra la categoría") );

        return new CategoryDTO(c.getName(), c.getSongs());

    }
}
