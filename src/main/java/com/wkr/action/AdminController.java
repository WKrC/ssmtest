package com.wkr.action;

import com.wkr.bean.AdminBean;
import com.wkr.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> adminLogin(AdminBean adminBean) {
        Map result = new HashMap();
        AdminBean resultBean = adminService.fetchAdminInfo(adminBean);
        if (resultBean == null) {
            result.put("resultCode", -1);
        } else {
            result.put("resultCode", 1);
        }
        return result;
    }
}
