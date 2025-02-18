package com.babel.babelfy.controller;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    //PUT
    @PutMapping("/{id}")
    public CategoryDTO put(@PathVariable long id, @RequestBody Map<String, String> body){
        String name = body.get("name");
        return categoryService.put(id,name);
    }
    //GET ALL
      @GetMapping ("")
    public List<CategoryDTO> getAll(){

        return  categoryService.getListCategory();
    }

    //DELETE

    @DeleteMapping("/{id}")
    public CategoryDTO deleteById (@PathVariable long id){
        return categoryService.deleteCategory(id);
    }
    //GET BY ID

    @GetMapping("/{id}")
    public CategoryDTO getById(@PathVariable long id){
        return categoryService.getById(id);
    }

}



