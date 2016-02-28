package com.example.wes.localdbsearch.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.wes.localdbsearch.model.Card;
import com.example.wes.localdbsearch.model.SearchResult;

import java.util.ArrayList;

/**
 * Created by wes on 16. 2. 26.
 */
public class CardDAO extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CARDDB";

    private static final String CARDS_VIRTUAL_TABLE = "CARDS";


    public CardDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CARD_TABLE = "CREATE VIRTUAL TABLE " + CARDS_VIRTUAL_TABLE + " USING fts3 ( " +
                Card.CARD_COL.NAME.getColumnName() + ", " +
                Card.CARD_COL.COMPANY.getColumnName() + ", " +
                Card.CARD_COL.DEPARTEMENT.getColumnName() + ", " +
                Card.CARD_COL.POSITION.getColumnName() + ", " +
                Card.CARD_COL.PHONE.getColumnName() + ", " +
                Card.CARD_COL.ADDRESS.getColumnName() + ")";

        db.execSQL(CREATE_CARD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS books");

        this.onCreate(db);
    }

    public void insertDB(Card card) {
        Log.d("inserDB in CardDAO", card.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Card.CARD_COL.NAME.getColumnName(), card.getName());
        values.put(Card.CARD_COL.COMPANY.getColumnName(), card.getCompany());
        values.put(Card.CARD_COL.DEPARTEMENT.getColumnName(), card.getDepartment());
        values.put(Card.CARD_COL.POSITION.getColumnName(), card.getPosition());
        values.put(Card.CARD_COL.PHONE.getColumnName(), card.getPhone());
        values.put(Card.CARD_COL.ADDRESS.getColumnName(), card.getAddress());

        db.insert(CARDS_VIRTUAL_TABLE, null, values);

        db.close();
    }

    public ArrayList<SearchResult> searchDB(Card.CARD_COL key, String value) {
        ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();

        String selection = key.getColumnName() + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + value + "%"};

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(CARDS_VIRTUAL_TABLE);

        Cursor cursor = builder.query(this.getReadableDatabase(),
                null, selection, selectionArgs, null, null, null);

        SearchResult searchResult = null;
        Card card = null;

        if (cursor.moveToFirst()) {
            do {
                card = new Card(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

                searchResult = new SearchResult(card, key);
                searchResults.add(searchResult);

            } while (cursor.moveToNext());
        }

        return searchResults;
    }

    public void deleteAllDB() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(CARDS_VIRTUAL_TABLE, null, null);
    }
}
