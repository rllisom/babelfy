package com.babel.babelfy.controller;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PutMapping("/{id}")
    public CategoryDTO put(@PathVariable long id, @RequestBody Map<String, String> body){
        String name = body.get("name");
        return categoryService.put(id,name);
    }


}
