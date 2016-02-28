package com.example.wes.localdbsearch.model;

/**
 * Created by wes on 16. 2. 26.
 */
public class Card {

    public enum CARD_COL {
        NAME("name"),
        COMPANY("company"),
        DEPARTEMENT("department"),
        POSITION("position"),
        PHONE("phone"),
        ADDRESS("address");

        private String column;

        CARD_COL(String column) {
            this.column = column;
        }

        public String getColumnName() {
            return column;
        }
    }

    private String name;
    private String company;
    private String department;
    private String position;
    private String phone;
    private String address;

    public Card(String name, String company, String department, String position, String phone, String address) {
        this.name = name;
        this.company = company;
        this.department = department;
        this.position = position;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String convertNameOfColumn(Card.CARD_COL cardColumn) {
        String columnName = "";
        switch (cardColumn) {
            case NAME:
                columnName = "이름";
                break;
            case COMPANY:
                columnName = "회사";
                break;
            case DEPARTEMENT:
                columnName = "부서";
                break;
            case POSITION:
                columnName = "직책";
                break;
            case PHONE:
                columnName = "연락처";
                break;
            case ADDRESS:
                columnName = "주소";
                break;
            default:
                break;
        }

        return columnName;
    }

    public String getValueByCardColum(Card.CARD_COL cardColumn) {
        String keyoword = "";
        switch (cardColumn) {
            case NAME:
                keyoword = getName();
                break;
            case COMPANY:
                keyoword = getCompany();
                break;
            case DEPARTEMENT:
                keyoword = getDepartment();
                break;
            case POSITION:
                keyoword = getPosition();
                break;
            case PHONE:
                keyoword = getPhone();
                break;
            case ADDRESS:
                keyoword = getAddress();
                break;
            default:
                break;
        }

        return keyoword;
    }
}
