package com.gw.crm.workbench.service.impl;

import com.gw.crm.workbench.service.ClueService;
import com.gw.crm.utils.SqlSessionUtil;
import com.gw.crm.workbench.dao.ClueDao;

public class ClueServiceImpl implements ClueService {
    ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
}
