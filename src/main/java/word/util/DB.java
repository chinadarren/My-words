package word.util;

import java.sql.*;

import com.mysql.jdbc.Driver;

/**
 * Created by lei.chen on 4/20/2015.
 */
public class DB {
    //定义用于连接数据库的常量
    private static final String URL ="jdbc:mysql://localhost:3306/words";
    private static final String USER = "root";
    private static final String PASSWORD = "system";
   //关联数据库
    public static Connection getConnection(){

        try {

            new Driver();

            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return null;
    }
//关闭数据相关操作
    public static void close(ResultSet resultSet,PreparedStatement preparedStatement,Connection connection){

        if(resultSet !=null){

            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(preparedStatement !=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection !=null){

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
//测试关联
    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
