package Utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName JdbcDruidMysql
 * @Description TODO
 * @Author wk
 * @Date 2020/6/11 0011 16:26
 * @Version 1.0
 */
public class JdbcDruidMysql {
    public static DruidDataSource dataSource;

    static {
        //使用软编码通过配置文件
        try {
            Properties properties = new Properties();
            //通过类加载器加载配置文件
            InputStream inputStream = JdbcDruidMysql.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 功能描述: 
     * @param: 关闭连接
     * @return: void
     * @auther: wk
     * @date: 2020/6/11 0011 16:56
     */
    public static void closeAll(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
