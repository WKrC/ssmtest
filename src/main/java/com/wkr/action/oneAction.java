package com.wkr.action;

import com.wkr.bean.UserBean;
import com.wkr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class oneAction {

    @Autowired
    UserService userService;

    @RequestMapping("/oneAction")
    @ResponseBody
    public List<Object> returnOne(String name, String password){
        List<Object> list = new ArrayList<>();

        list.add(name);
        return list;
    }
    @RequestMapping(value = "/twoAction", method = RequestMethod.POST)
    public String returnTwo(String name, String password){
        UserBean userBean = new UserBean();
        userBean.setName(name);
        userBean.setPassword(password);
        userService.saveUser(userBean);
        return "two";
    }
}
