package com.telda.twodb.controllers;

import com.telda.twodb.mappers.OneMapper;
import com.telda.twodb.mappers.TwoMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.tags.form.TagWriter;

import java.util.HashMap;
import java.util.Map;

@RestController
@Data
public class Controller {
    @Autowired
    OneMapper oneMapper;

    @Autowired
    TwoMapper twoMapper;

    @RequestMapping("/test_mappers")
    public Map<String,String> testMappers(){
        Map<String,String> result = new HashMap<>();
        result.put("Mapper","Database version");
        result.put("oneMapper",oneMapper.getVersion());
        result.put("twoMapper",twoMapper.getVersion());
        return result;
    }
}
