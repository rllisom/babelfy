package com.babel.babelfy.service;

import com.babel.babelfy.model.Category;
import com.babel.babelfy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {


    private final CategoryRepository repo;

    public Category showCategory(long id){
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra la categoría") );
    }
}
