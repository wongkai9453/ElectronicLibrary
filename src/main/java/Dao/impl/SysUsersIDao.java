package Dao.impl;

import Dao.ISysUserDao;
import Utils.JdbcUtils;
import bean.SysUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName SysUsersIDao
 * @Description 用户信息dao
 * @Author wk
 * @Date 2020/6/12 0012 17:06
 * @Version 1.0
 */
public class SysUsersIDao implements ISysUserDao {

    Logger log = LoggerFactory.getLogger(SysUsersIDao.class);

    @Override
    public SysUsers querySysUsers(String username) throws Exception {
        String sql = "select * from sys_users where username ='"+username+"'";
        SysUsers user = JdbcUtils.getObject(SysUsers.class,sql);
        if(user != null){
            return user;
        }
        return null;
    }
}
