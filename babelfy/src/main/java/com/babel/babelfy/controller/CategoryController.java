package com.babel.babelfy.controller;

import com.babel.babelfy.dto.CategoryDTO;
//import com.babel.babelfy.dto.CreateCategoryRequest;
//import com.babel.babelfy.dto.CreateCategoryResponse;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.service.CategoryService;
import com.babel.babelfy.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/categories")
@RequiredArgsConstructor
@RestController
public class CategoryController {


    private final CategoryService categoryService;


    @GetMapping ("")
    public List<CategoryDTO> getAll(){

        return  categoryService.getListCategory();
    }

    @GetMapping ("/{id}")
    public CategoryDTO getById(@PathVariable long id){

        Category category = categoryService.getCategoryById(id);
        CategoryDTO categoryDTO = categoryService.buildDTO(category);

        return  categoryDTO;
    }

    @DeleteMapping("/{id}")
    public CategoryDTO deleteById (@PathVariable long id){
        return categoryService.deleteCategory(id);
    }


//    @PostMapping ("")
//    public CreateCategoryResponse create(@RequestBody CreateCategoryRequest c){
//       return categoryService.create(c);
//    }





}
