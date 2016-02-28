package com.example.wes.localdbsearch.controller;

import com.example.wes.localdbsearch.model.Card;
import com.example.wes.localdbsearch.model.CardEntity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by wes on 16. 2. 26.
 */
public class RandomCardCreator {
    private Random random = new Random();
    private CardEntityFactory cardEntityFactory = new CardEntityFactory();

    public Card createRandomCard() {
        CardEntity nameEntity = null;

        if (random.nextInt() % 2 == 0)
            nameEntity = randomKoreanNameCreator();
        else
            nameEntity = randomEnglishNameCreator();

        CardEntity companyEntity = randomCompanyCreator();
        CardEntity departmentEntity = randomDepartmentCreator();
        CardEntity positionEntity = randomPositionCreator();
        CardEntity phoneEntity = randomPhonenCreator();
        CardEntity addressEntity = randomAddressCreator();


        ArrayList<CardEntity> cardEntities = new ArrayList<CardEntity>();
        cardEntities.add(nameEntity);
        cardEntities.add(companyEntity);
        cardEntities.add(departmentEntity);
        cardEntities.add(positionEntity);
        cardEntities.add(phoneEntity);
        cardEntities.add(addressEntity);

        Card card = new Card(cardEntities);

        return card;
    }

    public CardEntity randomKoreanNameCreator() {
        String koreanLastNames[] = {"이", "박", "최", "김", "한", "방"};
        String koreanFirstNames[] = {"가든", "기현", "길상", "나나", "상철"};
        String randomName = "";
        randomName = koreanLastNames[random.nextInt(koreanFirstNames.length)] + koreanFirstNames[random.nextInt(koreanFirstNames.length)];

        CardEntity cardEntity = cardEntityFactory.createCarEntity(CardEntityFactory.CARD_ENTITY_TYPE.NAME, randomName);
        return cardEntity;
    }

    public CardEntity randomEnglishNameCreator() {
        String englishLastNames[] = {"Aaron", "Abbott", "Carroll", "Connell", "Cyril"};
        String englishFirstNames[] = {"Liam", "Noah", "Ethan", "Mason", "Lucas"};
        String randomName = "";
        randomName = englishFirstNames[random.nextInt(englishFirstNames.length)] + " " + englishLastNames[random.nextInt(englishLastNames.length)];

        CardEntity cardEntity = cardEntityFactory.createCarEntity(CardEntityFactory.CARD_ENTITY_TYPE.NAME, randomName);
        return cardEntity;
    }

    public CardEntity randomCompanyCreator() {
        String companyList[] = {"Bodybook", "MC", "Samgsong", "DramaNCompany", "Orakle"};
        String randomCompany = companyList[random.nextInt(companyList.length)];

        CardEntity cardEntity = cardEntityFactory.createCarEntity(CardEntityFactory.CARD_ENTITY_TYPE.COMPANY, randomCompany);
        return cardEntity;
    }

    public CardEntity randomDepartmentCreator() {
        String departmentList[] = {"dev", "개발", "고리2사업소", "교육", "영업"};
        String randomDepartment = departmentList[random.nextInt(departmentList.length)];

        CardEntity cardEntity = cardEntityFactory.createCarEntity(CardEntityFactory.CARD_ENTITY_TYPE.DEPARTMENT, randomDepartment);
        return cardEntity;
    }

    public CardEntity randomPositionCreator() {
        String positionList[] = {"회장", "사장", "부장", "과장", "평사원", "President", "Chairman", "Manager", "Deputy Manager", "Staff"};
        String randomPosition = positionList[random.nextInt(positionList.length)];

        CardEntity cardEntity = cardEntityFactory.createCarEntity(CardEntityFactory.CARD_ENTITY_TYPE.POSITION, randomPosition);
        return cardEntity;
    }

    public CardEntity randomPhonenCreator() {
        String randomPhone = "010";
        for (int j = 0; j < 8; j++) {
            randomPhone = randomPhone + String.valueOf(random.nextInt(10));
        }

        CardEntity cardEntity = cardEntityFactory.createCarEntity(CardEntityFactory.CARD_ENTITY_TYPE.PHONE, randomPhone);
        return cardEntity;
    }

    public CardEntity randomAddressCreator() {
        String addressList[] = {
                "순천시 조례동", "서울특별시", "전남", "순천시 기적의도서관길 95", "Canada"
        };
        String randomAddress = addressList[random.nextInt(addressList.length)];

        CardEntity cardEntity = cardEntityFactory.createCarEntity(CardEntityFactory.CARD_ENTITY_TYPE.ADDRESS, randomAddress);

        return cardEntity;
    }
}
