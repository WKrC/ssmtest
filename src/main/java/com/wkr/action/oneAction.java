package com.wkr.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class oneAction {

    @RequestMapping("/oneAction")
    @ResponseBody
    public List<Object> returnOne(){
        List<Object> list = new ArrayList<>();
        list.add("呵呵姑娘");
        return list;
    }
    @RequestMapping(value = "/twoAction", method = RequestMethod.POST)
    public String returnTwo(){
        return "skip";
    }
}
