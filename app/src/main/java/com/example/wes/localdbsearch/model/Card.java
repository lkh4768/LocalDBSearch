package com.example.wes.localdbsearch.model;

import com.example.wes.localdbsearch.controller.CardEntityFactory;

import java.util.ArrayList;

/**
 * Created by wes on 16. 2. 28.
 */
public class Card {
    ArrayList<CardEntity> cardEntities = null;

    public Card(ArrayList<CardEntity> cardEntities) {
        this.cardEntities = cardEntities;
    }

    public String getValue(CardEntityFactory.CARD_ENTITY_TYPE cardEntityType) {
        for (CardEntity cardEntity : cardEntities) {
            if (cardEntity.getEnglishNameOfType() == cardEntityType.getType()) {
                return cardEntity.getValue();
            }
        }

        return null;
    }

    public String getKeywordOfKoreaNameOfType() {
        for (CardEntity cardEntity : cardEntities) {
            if (cardEntity.isKeyword() == true) {
                return cardEntity.getKoreaNameOfType();
            }
        }
        return null;
    }

    public String getKeywordValue() {
        for (CardEntity cardEntity : cardEntities) {
            if (cardEntity.isKeyword() == true) {
                return cardEntity.getValue();
            }
        }
        return null;
    }

    public void setIsKeywordOfCardEntity(CardEntityFactory.CARD_ENTITY_TYPE cardEntityType) {
        for (CardEntity cardEntity : cardEntities) {
            if (cardEntity.getEnglishNameOfType() == cardEntityType.getType())
                cardEntity.setIsKeyword(true);
        }
    }
}
