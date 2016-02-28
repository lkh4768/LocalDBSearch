package com.example.wes.localdbsearch.model;

/**
 * Created by wes on 16. 2. 26.
 */
public class SearchResult {
    public Card card;
    public Card.CARD_COL searchColumn;

    public SearchResult(Card card, Card.CARD_COL searchColumn) {
        this.card = card;
        this.searchColumn = searchColumn;
    }


    public Card getCard() {
        return card;
    }

    public String getSearchColumn() {
        String columnName = card.convertNameOfColumn(searchColumn);

        return columnName;
    }

    public String getSearchKeyword() {
        String keyoword = card.getValueByCardColum(searchColumn);
        return keyoword;
    }
}
