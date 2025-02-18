package com.babel.babelfy.controller;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public CategoryDTO add(@RequestBody String name){
        return categoryService.add(name);
    }

}
