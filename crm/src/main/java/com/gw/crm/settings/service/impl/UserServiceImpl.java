package com.gw.crm.settings.service.impl;

import com.gw.crm.settings.dao.UserDao;
import com.gw.crm.settings.service.UserService;
import com.gw.crm.utils.SqlSessionUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
}
