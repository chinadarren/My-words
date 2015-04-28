package word.action;

import word.model.Word;
import word.util.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lei.chen on 4/20/2015.
 */
@WebServlet(value = "/word")
public class WordAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.equals("list")) {
            System.out.println("word doPost list");
            list(req, resp);
        }
        if (action.equals("add")) {
            System.out.println("add");
            add(req, resp);
        }
        if (action.equals("query")) {
            query(req, resp);
        }
        if (action.equals("search")) {
            search(req, resp);
        }
        if (action.equals("modify")) {
            modify(req, resp);
        }
        if (action.equals("remove")) {
            remove(req, resp);
        }
    }

    private void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {

        Connection connection = DB.getConnection();
        String sql = "update word set english = ?, property = ?, chinese = ?, level = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, req.getParameter("english"));
            preparedStatement.setString(2, req.getParameter("property"));
            preparedStatement.setString(3, req.getParameter("chinese"));
            preparedStatement.setString(4, req.getParameter("level"));
            preparedStatement.setInt(5, Integer.parseInt(req.getParameter("id")));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
        resp.sendRedirect("/word?action=list");
    }

    private void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {

        Connection connection = DB.getConnection();
        String sql = "delete from word WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("id")));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
        resp.sendRedirect("/word?action=list");
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException{
        Connection connection = DB.getConnection();
        String sql = "select * from word WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("id")));
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Word word = new Word(resultSet.getInt("id"), resultSet.getString("english"), resultSet.getString("property"), resultSet.getString("chinese"), resultSet.getString("level"));
            req.setAttribute("word", word);
            req.getRequestDispatcher("/word/modify.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, preparedStatement, connection);
        }
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
//        Connection connection = DB.getConnection();
//        String sql = "select * from word WHERE english like ? OR chinese like ?";
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        String keyword = req.getParameter("keyword");
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, "%" + keyword + "%");
//            preparedStatement.setString(2, "%" + keyword + "%");
//            System.out.println(preparedStatement.toString());
//            resultSet = preparedStatement.executeQuery();
//            List<Word> words = new ArrayList<Word>();
//            while (resultSet.next()) {
//                Word word = new Word(resultSet.getInt("id"), resultSet.getString("english"), resultSet.getString("property"), resultSet.getString("chinese"), resultSet.getString("level"));
//                words.add(word);
//            }
//            req.getSession().setAttribute("words", words);
//            resp.sendRedirect("/home.jsp");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DB.close(resultSet, preparedStatement, connection);
//        }

    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException{
        System.out.println("add1");
        Connection connection =DB.getConnection();
        String sql = "INSERT INTO word VALUES(NULL, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        System.out.println(req.getParameter("chinese"));

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,req.getParameter("english"));
            preparedStatement.setString(2,req.getParameter("property"));
            preparedStatement.setString(3,req.getParameter("chinese"));
            preparedStatement.setString(4,req.getParameter("level"));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DB.close(null,preparedStatement,connection);
        }
        resp.sendRedirect("/word?action=list");

    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = DB.getConnection();
        String sql = "select * from word order by id DESC";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<Word> words = new ArrayList<Word>();
            while (resultSet.next()) {
                Word word = new Word(resultSet.getInt("id"), resultSet.getString("english"), resultSet.getString("property"), resultSet.getString("chinese"), resultSet.getString("level"));
                words.add(word);
            }
            req.getSession().setAttribute("words", words);
            resp.sendRedirect("/home.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, preparedStatement, connection);
        }
    }
    //注意提供 doGet方法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}




