package com.poi.imports.utils;

public enum MessageUtils {
    BLANK("10011","空白"),
    NOWAY("10012","路径不正确"),
    ARGERROR("10013","非法参数");
    private String key;
    private String value;
    private MessageUtils(String key,String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
