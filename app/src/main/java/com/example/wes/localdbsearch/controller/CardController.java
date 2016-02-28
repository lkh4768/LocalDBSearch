package com.example.wes.localdbsearch.controller;

import android.content.Context;

import com.example.wes.localdbsearch.db.CardDAO;
import com.example.wes.localdbsearch.model.Card;
import com.example.wes.localdbsearch.model.SearchResult;

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

        for (int i = 0; i < 100; i++) {
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

    public ArrayList<SearchResult> searchCards(String keyword) {
        ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();
        ArrayList<SearchResult> partOfSearchResults = null;

        for (Card.CARD_COL cardsCol : Card.CARD_COL.values()) {
            partOfSearchResults = cardDAO.searchDB(cardsCol, keyword);
            searchResults.addAll(partOfSearchResults);
        }
        return searchResults;
    }

    public void deleteAllCards() {
        cardDAO.deleteAllDB();
    }
}
