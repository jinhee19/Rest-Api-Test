package com.najinji.controller;

import com.najinji.domain.data.Data;
import com.najinji.domain.data.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // 전체 관광지 정보 가져옴
    @GetMapping(value="/select")
    public List<Data> list(){
       return repository.findAll();
    }

    // 전체 관광지의 이름 가져옴
    @GetMapping(value="/titles")
    public List<String> getTitle(){
        /*Optional<Data> option = repository.findById(title);
        String name = option.get().getTitle();
        return name;
         */
        List<Data> lists = repository.findAll();
        List<String> titles = new ArrayList<>(300);

        for(int i = 0; i < lists.size(); i++)
            titles.add(lists.get(i).getTitle());

        return titles;
    }



}
