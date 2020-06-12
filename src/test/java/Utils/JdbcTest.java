package Utils;

import bean.Tcount;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wk
 * @title: JdbcTest
 * @projectName MaveMysqlJDBC
 * @description: TODO
 * @date 2020/6/1121:09
 */
public class JdbcTest {

    private Logger log = LoggerFactory.getLogger(JdbcTest.class);
    /**
     * @Author wk
     * @Description: 测试查询是否成功
     * @Date 2020/6/11 21:18
     * @Param: []
     * @return: void
     **/
    @Test
    public void testdbcQuery(){
        String sql = "select tid,tname,tcode from tcount";
        List<Tcount> tcountList = JdbcUtils.JdbcQueryPstmt(Tcount.class,sql);
        for (int i = 0; i <tcountList.size() ; i++) {
            System.out.println("tid:["+tcountList.get(i).getTid()+"]*******tname:["+tcountList.get(i).getTname()+"]*******tcode:["+tcountList.get(i).getTcode()+"]");
        }
    }

    //添加信息
    @Test
    public void testJdbcinsert(){
        String sql = "insert into tcount(tid,tname,tcode) values (3,'Janpa','Jan')";
        int num = JdbcUtils.JdbcInsert(sql);
        assertEquals(1,num);
    }

    //修改信息
    @Test
    public void testJdbcUpdate(){
        String sql = "update tcount set tname = 'Chainese' where tid = 2";
        int num = JdbcUtils.JdbcInsert(sql);
        assertEquals(1,num);
    }

    //查询count(1)
    @Test
    public void testJdbcQuery(){
        String sql = "select count(1) snum from tcount";
        int snum =Integer.valueOf(JdbcUtils.getValue(sql));
        log.info(String.valueOf(snum));
        assertEquals(2,snum);
    }


    @Test
    public void testJdbcValue(){
        String sql = "select tname from tcount where tid = 2";
        String s = JdbcUtils.getValue(sql);
        log.info(s);
    }


    //查询对象如果是多条数据则返回第一题数据
    @Test
    public void testJdbcObject(){
        String sql = "select * from tcount ";
        Tcount s = JdbcUtils.getObject(Tcount.class,sql);
        log.info(String.valueOf(s.getTid()));
        log.info(s.getTname());
        log.info(s.getTcode());
    }

}
