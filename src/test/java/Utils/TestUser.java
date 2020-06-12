package Utils;

import Dao.ISysUserDao;
import Dao.impl.SysUsersIDao;
import bean.SysUsers;
import org.junit.Test;

/**
 * @ClassName TestUser
 * @Description TODO
 * @Author wk
 * @Date 2020/6/12 0012 17:11
 * @Version 1.0
 */
public class TestUser {

    @Test
    public void queryUser(){
        ISysUserDao s = new SysUsersIDao();
        try {
            SysUsers users = s.querySysUsers("wk");
            if(users !=null){
                System.out.println(users.getUsername());
                System.out.println(users.getChinesename());
                System.out.println(users.getPassword());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
