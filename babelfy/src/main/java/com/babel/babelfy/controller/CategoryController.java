package com.babel.babelfy.controller;

import com.babel.babelfy.dto.CategoryDTO;
import com.babel.babelfy.model.Category;
import com.babel.babelfy.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

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

    //GET BY ID
    @GetMapping ("/{id}")
    public CategoryDTO getById(@PathVariable long id){

        Category category = categoryService.getCategoryById(id);
        CategoryDTO categoryDTO = categoryService.buildDTO(category);

        return  categoryDTO;
    }

    //DELETE
    @DeleteMapping("/{id}")
    public CategoryDTO deleteById (@PathVariable long id){
        return categoryService.deleteCategory(id);
    }
    //POST
    @PostMapping("")
    public CategoryDTO add(@RequestBody CategoryDTO dto){
        return categoryService.add(dto);
    }
}



