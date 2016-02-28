package com.example.wes.localdbsearch.model;

/**
 * Created by wes on 16. 2. 28.
 */
public class CardEntity {
    private String englishNameOfType = "";
    private String koreaNameOfType = "";
    private String value = "";
    private boolean isKeyword = false;

    public CardEntity(String englishNameOfType, String koreaNameOfType, String value) {
        this.englishNameOfType = englishNameOfType;
        this.koreaNameOfType = koreaNameOfType;
        this.value = value;
    }

    public String getEnglishNameOfType() {
        return englishNameOfType;
    }

    public void setEnglishNameOfType(String englishName) {
        this.englishNameOfType = englishName;
    }

    public String getKoreaNameOfType() {
        return koreaNameOfType;
    }

    public void setKoreaNameOfType(String koreaName) {
        this.koreaNameOfType = koreaName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isKeyword() {
        return isKeyword;
    }

    public void setIsKeyword(boolean isKeyword) {
        this.isKeyword = isKeyword;
    }
}
