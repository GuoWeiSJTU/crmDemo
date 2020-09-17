import com.gw.crm.settings.dao.UserDao;
import com.gw.crm.settings.domain.User;
import com.gw.crm.utils.SqlSessionUtil;

import java.util.List;

public class test {
    public static void main(String[] args) {
        UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
        List<User> userList= userDao.getUserList();
        for (User user:userList){
            System.out.println(user.getName());
        }
    }
}
