package com.babel.babelfy.service;

import com.babel.babelfy.model.Category;
import com.babel.babelfy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category modifyCategory(Category c){
            return repo.save(c);
    }

}
