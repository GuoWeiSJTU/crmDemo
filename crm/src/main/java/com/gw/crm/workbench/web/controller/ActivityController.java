package com.gw.crm.workbench.web.controller;

import com.gw.crm.settings.domain.User;
import com.gw.crm.settings.service.UserService;
import com.gw.crm.settings.service.impl.UserServiceImpl;
import com.gw.crm.utils.DateTimeUtil;
import com.gw.crm.utils.PrintJson;
import com.gw.crm.utils.ServiceFactory;
import com.gw.crm.utils.UUIDUtil;
import com.gw.crm.vo.PaginationVO;
import com.gw.crm.workbench.domain.Activity;
import com.gw.crm.workbench.service.ActivityService;
import com.gw.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author 北京动力节点
 */
public class ActivityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入到市场活动控制器");

        String path = request.getServletPath();

        if("/workbench/activity/getUserList.do".equals(path)){
            getUserList(request,response);
        }else if ("/workbench/activity/save.do".equals(path)){
            save(request,response);
        }else if ("/workbench/activity/pageList.do".equals(path)){
            pageList(request,response);
        }

    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNoStr = request.getParameter("pageNo");
        Integer pageNo = Integer.parseInt(pageNoStr);
        String pageSizeStr = request.getParameter("pageSize");
        Integer pageSize = Integer.parseInt(pageSizeStr);
        int skipCount = (pageNo-1) * pageSize;

        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        PaginationVO<Activity> paginationVO = activityService.pageList(map);
        PrintJson.printJsonObj(response,paginationVO);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {

        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        //创建时间：当前系统时间
        String createTime = DateTimeUtil.getSysTime();
        //创建人：当前登录用户
        String createBy = ((User)request.getSession(false).getAttribute("user")).getName();

        Activity activity = new Activity();
        activity.setId(id);
        activity.setCost(cost);
        activity.setStartDate(startDate);
        activity.setOwner(owner);
        activity.setName(name);
        activity.setEndDate(endDate);
        activity.setDescription(description);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);

        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = activityService.save(activity);

        PrintJson.printJsonFlag(response, flag);
    }

    private void getUserList(HttpServletRequest request,HttpServletResponse response) {
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = userService.getUserList();
        PrintJson.printJsonObj(response,userList);
    }

}




































