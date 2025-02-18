package com.babel.babelfy.service;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Builder
@RequiredArgsConstructor
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Transactional
    public CategoryDTO add(@RequestBody String name){
        return categoryRepository.save(c);
    }

    public Category categoryDTOtoCategory (CategoryDTO C){

    }

}
