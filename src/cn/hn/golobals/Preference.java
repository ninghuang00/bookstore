package cn.hn.golobals;

/**
 * Created by huangning on 2017/9/12.
 */
public enum Preference {
    SING("sing","唱歌"),DANCE("dance","跳舞"),FOOTBALL("football","足球");
    private String name;
    private String value;
    private Preference(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
