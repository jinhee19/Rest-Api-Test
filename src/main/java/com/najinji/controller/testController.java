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

    @GetMapping(value="/select")
    public List<Data> list(){
       return repository.findAll();
    }

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

    // flask에서 추천지 5개 가져오기
    @GetMapping(value="/recommend")
    public List<String> getRecommend(){
        List<String> recommends = new ArrayList<>();
        
        return recommends;
    }

    // 각 추천지에 대한 정보 
    @GetMapping(value="/select/{title}")
    public Data getPlace(@PathVariable("title") String title){
        return repository.findById(title).orElse(null);
    }
    
    // flask 서버에 검색명 전달

}
