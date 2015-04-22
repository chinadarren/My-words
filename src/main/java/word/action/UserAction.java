package word.action;

import org.apache.commons.codec.digest.DigestUtils;
import word.util.Constant;
import word.util.DB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by lei.chen on 4/20/2015.
 */
//
@WebServlet(value = "/user")
public class UserAction extends HttpServlet {
//接收表单post发送的消息
@Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
    //action接收表达name为action的value值
    String action = req.getParameter("action");
    //判断表单提交的value值
    if(action.equals("login")){
        //调用login方法
        login(req, resp);
    }
    if(action.equals("signup")){
        signup(req,resp);
    }
    if (action.equals("logout")) {
        logout(req, resp);
    }
    if (action.equals("isUsernameExist")) {
        isUsernameExist(req, resp);
    }
}
//判断用户是否存在
    private void isUsernameExist(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        System.out.println("is exist");
        String username = req.getParameter("username");
        System.out.println("username: " + username);
        Connection connection = DB.getConnection();
        String sql = "SELECT * FROM user WHERE username=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resp.setContentType("text/html, charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            if (resultSet.next()) {
                System.out.println("result ture");
                writer.print("true");
            } else {
                System.out.println("result false");
                writer.print("false");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, preparedStatement, connection);
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        //关闭Session
        req.getSession().invalidate();
        resp.sendRedirect("/index.jsp");

    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Connection connection = DB.getConnection();

        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,DigestUtils.md5Hex(password + Constant.SALT));
            //连接输入做查询处理
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                req.getSession().setAttribute("username", username);
                resp.sendRedirect("/word?action=list");//一次新的请求
            }else{
                //返回一个message消息，index页面调用
                req.setAttribute("message", "invalid username or password!");

                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, preparedStatement, connection);
        }
    }
    private void signup(HttpServletRequest req, HttpServletResponse resp) {
        //接收表单name为username/passwrod的value值
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//创建关联数据库对象
        Connection connection = DB.getConnection();
        //创建输入数据库语句
        String sql = "insert into user values(null,?,?)";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2, DigestUtils.md5Hex(password + Constant.SALT));
            //连接数据库做更新处理
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(null,preparedStatement,connection);
        }
        try {
            //跳转页面
            resp.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//注意提供 doGet方法
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        doPost(req,resp);
    }
}
