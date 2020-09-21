package com.gw.crm.settings.web.controller;

import com.gw.crm.settings.domain.User;
import com.gw.crm.settings.service.UserService;
import com.gw.crm.settings.service.impl.UserServiceImpl;
import com.gw.crm.utils.MD5Util;
import com.gw.crm.utils.PrintJson;
import com.gw.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        System.out.println(path);
        if("/settings/user/login.do".equals(path)){
            login(request,response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");

        loginPwd = MD5Util.getMD5(loginPwd);

        String ip = request.getRemoteAddr();

        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());

        try {
            User user = userService.login(loginAct,loginPwd,ip);
            request.getSession().setAttribute("user",user);
            // {"suncess":true}
            String str ="{\"suncess\":true}";
//            response.getWriter().print(str);
            PrintJson.printJsonFlag(response,true);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);

        }
    }
}
