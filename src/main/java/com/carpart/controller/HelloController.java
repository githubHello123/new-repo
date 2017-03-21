package com.carpart.controller;

import com.carpart.dao.GirlRepository;
import com.carpart.entity.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/19.
 */
@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private GirlRepository girlRepository;

    @RequestMapping(value="/say",method = RequestMethod.GET)
    @ResponseBody
    public String say(){
        return "Hello spring boot";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Map<String,Object> map){
        List<Girl> list = girlRepository.findAll();
        map.put("list",list);
        return "girl/girlList";
    }
}
