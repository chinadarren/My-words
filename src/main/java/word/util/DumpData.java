package word.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/4/12.
 */
//将TXT文件导入数据库
public class DumpData {

    private static final String SQL = "INSERT INTO word VALUES(NULL, ?, ?, ?, 'CET-4')";
    private static final String[] REGEXPS = {"^[A-z]+", "[a-z]+\\.", "[\\u4e00-\\u9fbb\\s；，()…]+"};

    public static void main(String[] args) {
        int counter = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("doc/CET-4.txt"));
            String line;
            connection = DB.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            Matcher matcher;
            StringBuilder stringBuilder;
            while ((line = bufferedReader.readLine()) != null) {
                for (int i = 0; i < REGEXPS.length; i++) {
                    matcher = Pattern.compile(REGEXPS[i]).matcher(line);
                    stringBuilder = new StringBuilder();
                    while (matcher.find()) {
                        stringBuilder.append(matcher.group());
                    }
                    preparedStatement.setString((i + 1), stringBuilder.toString().trim());
                }
                preparedStatement.addBatch();
                counter++;
            }
            preparedStatement.executeBatch();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
        System.out.println(counter);
    }
}
