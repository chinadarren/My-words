package word.model;
import java.io.Serializable;

/**
 * Created by Administrator on 2015/4/18.
 * 模型类、数据类
 * 与数据库表对应
 */

public class Word implements Serializable {
    private Integer id;
    private String english;
    private String property;
    private String chinese;
    private String level;

    public Word() {
    }

    public Word(Integer id, String english, String property, String chinese, String level) {
        this.id = id;
        this.english = english;
        this.property = property;
        this.chinese = chinese;
        this.level = level;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", english='" + english + '\'' +
                ", property='" + property + '\'' +
                ", chinese='" + chinese + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
