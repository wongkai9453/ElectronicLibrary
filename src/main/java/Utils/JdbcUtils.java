package Utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//1、执行静态SQL语句。通常通过Statement实例实现。   Statement stmt = con.createStatement() ; sql注入是不安全
//2、执行动态SQL语句。通常通过PreparedStatement实例实现。  PreparedStatement pstmt = con.prepareStatement(sql) ;
//3、执行数据库存储过程。通常通过CallableStatement实例实现。  CallableStatement cstmt = con.prepareCall("{CALL demoSp(? , ?)}") ;

/**
 * @author wk
 * @title: JdbcUtils
 * @projectName MaveMysqlJDBC
 * @description:
 * jdbc查询，删除，修改，添加 公共方法
 * @date 2020/6/821:48
 */
public class JdbcUtils {
      /**
     * 功能描述:
     * @param: [tClass, sql, args]
     * 动态sql查询
     * @return: java.util.List<T>
     * @auther: wk
     * @date: 2020/6/9 0009 17:51
     */
    public static <T>List<T> JdbcQueryPstmt(Class<T> tClass,String sql,Object ...args){
        T entity  = null;
        List<T> list = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //创建数据库连接
            connection = JdbcDruidMysql.getConnection();
            //获取预处理对象，并依次给参数赋值
            pstmt = connection.prepareStatement(sql);
            //执行sql
            rs=pstmt.executeQuery();
            //创建 ResultSetMetaData 来获得数据库表的字段名等元数据
            ResultSetMetaData metaData = rs.getMetaData();
            Map<String,Object> map = new HashMap<String, Object>();
            list = new ArrayList<T>();
            while (rs.next()) {
                map.clear();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String columnKey = metaData.getColumnLabel(i+1);
                    Object columnValue = rs.getObject(columnKey);
                    map.put(columnKey,columnValue);
                }
                //偌map不为空，利用反射创建tclass对应的对象
                if(!map.isEmpty()){
                    try {
                        entity = tClass.newInstance();
                        //遍历map对象，利用反射为class对象赋值
                        for(Map.Entry<String,Object> entry : map.entrySet()){
                            String name = entry.getKey();
                            Object value = entry.getValue();
                            try {
                                //利用反射设置实体类字段
                                Field field = tClass.getDeclaredField(name);
                                field.setAccessible(true);
                                field.set(entity,value);
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            }

                        }

                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    list.add(entity);
                }
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcDruidMysql.closeAll(connection,pstmt,rs);
        }
        return list;
    }


    /**
     * 功能描述:
     * 保存修改方法
     * @param: [sql, args]
     * @return: int
     * @auther: wk
     * @date: 2020/6/9 0009 18:05
     */
    public static int JdbcInsert(String sql,Object ...args){
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        try {
            conn =  JdbcDruidMysql.getConnection();
            stmt = conn.prepareStatement(sql);
            for (int i = 0; i <args.length ; i++) {
                stmt.setObject(i+1,args[i]);
            }
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcDruidMysql.closeAll(conn,stmt,null);
        }
        return result;
    }


    /**
     * 功能描述:
     * 查询一个值
     * @param: [sql, args]
     * @return: E
     * @auther: wk
     * @date: 2020/6/9 0009 18:05
     */
    public static String getValue(String sql ,Object ...args){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn =  JdbcDruidMysql.getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i <args.length ; i++) {
                pstmt.setObject(i+1,args[i]);
            }
            rs = pstmt.executeQuery();
            if(rs.next()){
                return String.valueOf(rs.getObject(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcDruidMysql.closeAll(conn,pstmt,rs);
        }
        return null;
    }


    public static <T> T getObject(Class<T> tClass,String sql ,Object ...args){
        T entity  = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn =  JdbcDruidMysql.getConnection();
          //  sql = sql + "  order by tid asc limit 1";
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i <args.length ; i++) {
                pstmt.setObject(i+1,args[i]);
            }
            rs = pstmt.executeQuery();
            //创建 ResultSetMetaData 来获得数据库表的字段名等元数据
            ResultSetMetaData metaData = rs.getMetaData();
            Map<String,Object> map = new HashMap<String, Object>();
            if(rs.next()){
                map.clear();
                for (int i = 0; i <metaData.getColumnCount() ; i++) {
                    String columnKey = metaData.getColumnLabel(i+1);
                    Object columnValue = rs.getObject(columnKey);
                    map.put(columnKey,columnValue);
                }
                if(!map.isEmpty()){
                    try {
                        entity = tClass.newInstance();
                        //便利map对象。利用反射为class对象赋值
                        for (Map.Entry<String,Object> entry : map.entrySet()){
                            String name = entry.getKey();
                            Object value = entry.getValue();
                            try {
                                Field field = tClass.getDeclaredField(name);
                                field.setAccessible(true);
                                field.set(entity,value);
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcDruidMysql.closeAll(conn,pstmt,rs);
        }
        return null;
    }

}
