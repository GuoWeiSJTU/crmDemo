package com.gw.crm.workbench.service.impl;

import com.gw.crm.utils.SqlSessionUtil;
import com.gw.crm.workbench.dao.ActivityDao;
import com.gw.crm.workbench.domain.Activity;
import com.gw.crm.workbench.service.ActivityService;

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

}
