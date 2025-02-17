package com.babel.babelfy.controller;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/category")
@RequiredArgsConstructor
@RestController
public class CategoryController {


    private final CategoryService service;

    @GetMapping ("/")
    public List<CategoryDTO> getListCategory_Cont(){
        return  service.getListCategory_Ser();
    }

    @DeleteMapping("/deleteCat")
    public CategoryDTO deleteCategory_Cont (@RequestParam long id){
        return service.deleteCategory(id);
    }

    @PostMapping ("/addCategory")
    public Category addNewCategory_Cont(CategoryDTO c){
       return service.addNewCategory(c);
    }




}
