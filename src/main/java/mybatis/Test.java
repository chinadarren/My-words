package mybatis;

import org.apache.ibatis.session.SqlSession;
import word.model.User;

import java.util.List;

/**
 * Created by Administrator on 2015/4/25.
 */
public class Test {

    public static void main(String[] args) {
        insert();
//        update();
//        delete();
 //       select();
//        selectOne();
        System.out.println("done.");
    }

    private static void insert() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SessionFactoryUtil.getSqlSession();
            sqlSession.insert("user.insert", new User(null, "33334433", "123"));
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    private static void update() {
        SqlSession sqlSession = SessionFactoryUtil.getSqlSession();
        sqlSession.update("user.update", new User(6, "zhangsan", "456"));
        sqlSession.close();
    }

    private static void delete() {
        SqlSession sqlSession = SessionFactoryUtil.getSqlSession();
        sqlSession.update("user.delete", 8);
        sqlSession.close();
    }

    private static void select() {
        SqlSession sqlSession = SessionFactoryUtil.getSqlSession();
        List<User> users = sqlSession.selectList("user.select");
        sqlSession.close();
        System.out.println(users);
    }

    private static void selectOne() {
        SqlSession sqlSession = SessionFactoryUtil.getSqlSession();
        User user = sqlSession.selectOne("user.selectOne", 1);
        sqlSession.close();
        System.out.println(user);
    }
}
