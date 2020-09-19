package com.gw.crm.workbench.service.impl;

import com.gw.crm.utils.SqlSessionUtil;
import com.gw.crm.vo.PaginationVO;
import com.gw.crm.workbench.dao.ActivityDao;
import com.gw.crm.workbench.domain.Activity;
import com.gw.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

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

}
