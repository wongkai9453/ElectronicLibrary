package Dao;

import bean.SysUsers;

/**
 * @ClassName ISysUserDao
 * @Description TODO
 * @Author wk
 * @Date 2020/6/12 0012 17:03
 * @Version 1.0
 */
public  interface ISysUserDao {
    public SysUsers querySysUsers(String username) throws Exception;

}
