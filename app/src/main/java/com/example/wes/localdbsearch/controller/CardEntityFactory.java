package com.example.wes.localdbsearch.controller;

import com.example.wes.localdbsearch.model.AddressEntity;
import com.example.wes.localdbsearch.model.CardEntity;
import com.example.wes.localdbsearch.model.CompanyEntity;
import com.example.wes.localdbsearch.model.DepartmentEntity;
import com.example.wes.localdbsearch.model.NameEntity;
import com.example.wes.localdbsearch.model.PhoneEntity;
import com.example.wes.localdbsearch.model.PositionEntity;

/**
 * Created by wes on 16. 2. 28.
 */
public class CardEntityFactory {

    public enum CARD_ENTITY_TYPE {
        NAME("name"),
        COMPANY("company"),
        DEPARTMENT("department"),
        POSITION("position"),
        PHONE("phone"),
        ADDRESS("address");

        private String type;

        CARD_ENTITY_TYPE(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public CardEntity createCarEntity(CARD_ENTITY_TYPE cardEntityType, String value) {
        switch (cardEntityType) {
            case NAME:
                return new NameEntity(value);
            case COMPANY:
                return new CompanyEntity(value);
            case DEPARTMENT:
                return new DepartmentEntity(value);
            case POSITION:
                return new PositionEntity(value);
            case PHONE:
                return new PhoneEntity(value);
            case ADDRESS:
                return new AddressEntity(value);
            default:
                return null;
        }
    }
}
