import com.gw.crm.settings.dao.UserDao;
import com.gw.crm.settings.domain.User;
import com.gw.crm.utils.DateTimeUtil;
import com.gw.crm.utils.MD5Util;
import com.gw.crm.utils.SqlSessionUtil;

import java.util.List;

public class test {
    public static void main(String[] args) {
        UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
        List<User> userList= userDao.getUserList();
        for (User user:userList){
            System.out.println(user.getName());
        }
        String expireTime = "2020-10-10 10:10:10";
        String curTime = DateTimeUtil.getSysTime();
        System.out.println(curTime.compareTo(expireTime));
        System.out.println(MD5Util.getMD5("123"));
    }
}
