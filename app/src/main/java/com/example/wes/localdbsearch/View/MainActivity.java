package com.example.wes.localdbsearch.View;

import android.app.SearchManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.wes.localdbsearch.R;
import com.example.wes.localdbsearch.controller.CardController;
import com.example.wes.localdbsearch.model.Card;
import com.example.wes.localdbsearch.model.SearchResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private CardController cardController;
    private SearchView inputSearch;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputSearch = (SearchView) findViewById(R.id.inputSearch);
        listView = (ListView) findViewById(R.id.listView);

        cardController = new CardController(this);

        ArrayList<Card> cards = cardController.randomCardsCreator();
        cardController.deleteAllCards();
        cardController.insertCards(cards);

        inputSearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<SearchResult> searchResults = cardController.searchCards(newText);

        ListViewAdapter lva = new ListViewAdapter(this.getApplicationContext(), R.layout.list_item, searchResults);
        listView.setAdapter(lva);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        ArrayList<SearchResult> searchResults = cardController.searchCards(query);

        ListViewAdapter lva = new ListViewAdapter(this.getApplicationContext(), R.layout.list_item, searchResults);
        listView.setAdapter(lva);
        return false;
    }
}
