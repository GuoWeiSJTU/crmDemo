package com.gw.crm.workbench.service.impl;

import com.gw.crm.settings.dao.UserDao;
import com.gw.crm.settings.domain.User;
import com.gw.crm.utils.SqlSessionUtil;
import com.gw.crm.vo.PaginationVO;
import com.gw.crm.workbench.dao.ActivityDao;
import com.gw.crm.workbench.dao.ActivityRemarkDao;
import com.gw.crm.workbench.domain.Activity;
import com.gw.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public boolean save(Activity activity) {
        boolean flag = true;
        int count = activityDao.save(activity);
        if (count != 1){
            flag = false;
        }
        return flag;
    }

    @Override
    public PaginationVO<Activity> pageList(Map<String, Object> map) {

        int total = activityDao.getTotalByCondition(map);
        List<Activity> activityList = activityDao.getActivityByCondition(map);

        PaginationVO<Activity> paginationVO = new PaginationVO<>();
        paginationVO.setTotal(total);
        paginationVO.setDataList(activityList);
        return paginationVO;
    }

    @Override
    public boolean delete(String[] ids) {
        boolean flag = true;

        //查询需要删除备注的数量
        int count1 = activityRemarkDao.getCountByAids(ids);

        //删除备注，返回数量
        int count2 = activityRemarkDao.deleteByAids(ids);

        if(count1 != count2){
            flag = false;
        }
        //删除活动

        int count3 = activityDao.delete(ids);
        if(count3 != ids.length){
            flag = false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {
        Map<String,Object> map = new HashMap<>();
        List<User> userList = userDao.getUserList();
        Activity activity = activityDao.getById(id);
        map.put("uList",userList);
        map.put("a",activity);
        return map;
    }

    @Override
    public boolean update(Activity activity) {
        boolean flag = true;
        int count = activityDao.update(activity);
        if (count != 1){
            flag = false;
        }
        return flag;
    }

}
