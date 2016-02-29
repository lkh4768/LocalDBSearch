package com.example.wes.localdbsearch.controller;

import android.content.Context;
import android.util.Log;

import com.example.wes.localdbsearch.db.CardDAO;
import com.example.wes.localdbsearch.model.Card;

import java.util.ArrayList;

/**
 * Created by wes on 16. 2. 26.
 */
public class CardController {
    private CardDAO cardDAO;


    public CardController(Context context) {
        cardDAO = new CardDAO(context);
    }

    public ArrayList<Card> randomCardsCreator() {
        ArrayList<Card> cards = new ArrayList<Card>();
        Card card = null;
        RandomCardCreator randomCardCreator = new RandomCardCreator();

        for (int i = 0; i < 1000; i++) {
            card = randomCardCreator.createRandomCard();
            cards.add(card);
        }

        return cards;
    }

    public void insertCards(ArrayList<Card> cards) {
        for (Card card : cards) {
            cardDAO.insertDB(card);
        }
    }

    public ArrayList<Card> searchCards(String keyword) {
        ArrayList<Card> cards = new ArrayList<Card>();
        ArrayList<Card> partOfCards = null;

        if (!keyword.isEmpty()) {
            for (CardEntityFactory.CARD_ENTITY_TYPE cardEntityType : CardEntityFactory.CARD_ENTITY_TYPE.values()) {
                partOfCards = cardDAO.searchDB(cardEntityType, keyword);
                cards.addAll(partOfCards);
            }
        }
        return cards;
    }

    public void deleteAllCards() {
        cardDAO.deleteAllDB();
    }
}
