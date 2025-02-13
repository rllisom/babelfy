package com.babel.babelfy.controller;

import com.babel.babelfy.model.Category;
import com.babel.babelfy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService service;


    @PutMapping("")
    public String putCategory(@RequestBody Category c){
        if(service.modifyCategory(c)!=null){
            return "Cambio realizado correctamente";
        }else{
            return "Cambio fallido" ;
        }
    }

}
