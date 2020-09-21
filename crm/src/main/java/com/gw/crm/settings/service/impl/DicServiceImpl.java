package com.gw.crm.settings.service.impl;

import com.gw.crm.settings.dao.DicTypeDao;
import com.gw.crm.settings.dao.DicValueDao;
import com.gw.crm.settings.domain.DicType;
import com.gw.crm.settings.service.DicService;
import com.gw.crm.utils.SqlSessionUtil;

public class DicServiceImpl implements DicService {
    DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);
}
