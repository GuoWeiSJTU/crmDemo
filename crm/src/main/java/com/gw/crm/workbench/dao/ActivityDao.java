package com.gw.crm.workbench.dao;

import com.gw.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    int save(Activity activity);

    List<Activity> getActivityByCondition(Map<String, Object> map);

    int getTotalByCondition(Map<String, Object> map);
}
