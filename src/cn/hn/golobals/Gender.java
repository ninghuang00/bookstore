package cn.hn.golobals;

/**
 * Created by huangning on 2017/9/12.
 */
public enum Gender {
    MALE("male","男"),FEMALE("female","女");
    private String name;
    private String value;
    private Gender(String name,String value){
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
