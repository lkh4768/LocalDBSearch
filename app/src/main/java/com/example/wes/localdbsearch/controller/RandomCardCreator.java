package com.example.wes.localdbsearch.controller;

import com.example.wes.localdbsearch.model.Card;

import java.util.Random;

/**
 * Created by wes on 16. 2. 26.
 */
public class RandomCardCreator {
    private Random random = new Random();

    public Card createRandomCard() {
        String name = "";
        if (random.nextInt() % 2 == 0)
            name = randomKoreanNameCreator();
        else
            name = randomEnglishNameCreator();

        String company = randomCompanyCreator();
        String department = randomDepartmentCreator();
        String position = randomPositionCreator();
        String phone = randomPhonenCreator();

        String address = randomAddressCreator();
        Card card = new Card(name, company, department, position, phone, address);

        return card;
    }

    public String randomKoreanNameCreator() {
        String koreanLastNames[] = {"이", "박", "최", "김", "한", "방"};
        String koreanFirstNames[] = {"가든", "기현", "길상", "나나", "상철"};
        String randomName = "";
        randomName = koreanLastNames[random.nextInt(koreanFirstNames.length)] + koreanFirstNames[random.nextInt(koreanFirstNames.length)];
        return randomName;
    }

    public String randomEnglishNameCreator() {
        String englishLastNames[] = {"Aaron", "Abbott", "Carroll", "Connell", "Cyril"};
        String englishFirstNames[] = {"Liam", "Noah", "Ethan", "Mason", "Lucas"};
        String randomName = "";
        randomName = englishFirstNames[random.nextInt(englishFirstNames.length)] + " " + englishLastNames[random.nextInt(englishLastNames.length)];
        return randomName;
    }

    public String randomCompanyCreator() {
        String companyList[] = {"Bodybook", "MC", "Samgsong", "DramaNCompany", "Orakle"};
        String randomCompany = companyList[random.nextInt(companyList.length)];

        return randomCompany;
    }

    public String randomDepartmentCreator() {
        String departmentList[] = {"dev", "개발", "고리2사업소", "교육", "영업"};
        String randomDepartment = departmentList[random.nextInt(departmentList.length)];

        return randomDepartment;
    }

    public String randomPositionCreator() {
        String positionList[] = {"회장", "사장", "부장", "과장", "평사원", "President", "Chairman", "Manager", "Deputy Manager", "Staff"};
        String randomPosition = positionList[random.nextInt(positionList.length)];

        return randomPosition;
    }

    public String randomPhonenCreator() {
        String randomPhone = "010";
        for (int j = 0; j < 8; j++) {
            randomPhone = randomPhone + String.valueOf(random.nextInt(10));
        }

        return randomPhone;
    }

    public String randomAddressCreator() {
        String addressList[] = {
                "순천시 조례동", "서울특별시", "전남", "순천시 기적의도서관길 95", "Canada"
        };
        String randomAddress = addressList[random.nextInt(addressList.length)];

        return randomAddress;
    }
}
