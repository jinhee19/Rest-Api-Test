package com.najinji.controller;

import com.najinji.domain.data.Data;
import com.najinji.domain.data.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/test")
@RestController
public class testController {

    private DataRepository repository;

    @Autowired
    public testController(DataRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Data> list(){
       return repository.findAll();
    }

    /*@GetMapping(value="/{title}")
    public String getTitle(@PathVariable String title){
        Optional<Data> option = repository.findById(title);
        String name = option.get().getTitle();
        return name;
    }
*/
}
