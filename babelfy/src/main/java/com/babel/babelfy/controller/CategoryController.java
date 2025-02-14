package com.babel.babelfy.controller;

import com.babel.babelfy.model.Category;
import com.babel.babelfy.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    @PutMapping("/changeCategory")
    public String putCategory(@RequestBody Category c, String name){
        if(service.modifyCategory(c,name)!=null){
            return "Cambio realizado correctamente";
        }else{
            return "Cambio fallido" ;
        }
    }

}
