package com.example.wes.localdbsearch.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.wes.localdbsearch.model.Card;
import com.example.wes.localdbsearch.model.CardEntity;
import com.example.wes.localdbsearch.controller.CardEntityFactory;

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
                CardEntityFactory.CARD_ENTITY_TYPE.NAME.getType() + ", " +
                CardEntityFactory.CARD_ENTITY_TYPE.COMPANY.getType() + ", " +
                CardEntityFactory.CARD_ENTITY_TYPE.DEPARTMENT.getType() + ", " +
                CardEntityFactory.CARD_ENTITY_TYPE.POSITION.getType() + ", " +
                CardEntityFactory.CARD_ENTITY_TYPE.PHONE.getType() + ", " +
                CardEntityFactory.CARD_ENTITY_TYPE.ADDRESS.getType() + ")";

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

        values.put(CardEntityFactory.CARD_ENTITY_TYPE.NAME.getType(), card.getValue(CardEntityFactory.CARD_ENTITY_TYPE.NAME));
        values.put(CardEntityFactory.CARD_ENTITY_TYPE.COMPANY.getType(), card.getValue(CardEntityFactory.CARD_ENTITY_TYPE.COMPANY));
        values.put(CardEntityFactory.CARD_ENTITY_TYPE.DEPARTMENT.getType(), card.getValue(CardEntityFactory.CARD_ENTITY_TYPE.DEPARTMENT));
        values.put(CardEntityFactory.CARD_ENTITY_TYPE.POSITION.getType(), card.getValue(CardEntityFactory.CARD_ENTITY_TYPE.POSITION));
        values.put(CardEntityFactory.CARD_ENTITY_TYPE.PHONE.getType(), card.getValue(CardEntityFactory.CARD_ENTITY_TYPE.PHONE));
        values.put(CardEntityFactory.CARD_ENTITY_TYPE.ADDRESS.getType(), card.getValue(CardEntityFactory.CARD_ENTITY_TYPE.ADDRESS));

        db.insert(CARDS_VIRTUAL_TABLE, null, values);

        db.close();
    }

    public ArrayList<Card> searchDB(CardEntityFactory.CARD_ENTITY_TYPE cardEntityType, String value) {
        ArrayList<Card> cards = new ArrayList<Card>();

        String selection = cardEntityType.getType() + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + value + "%"};

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(CARDS_VIRTUAL_TABLE);

        Cursor cursor = builder.query(this.getReadableDatabase(),
                null, selection, selectionArgs, null, null, null);

        Card card = null;
        CardEntity nameEntity = null;
        CardEntity companyEntity = null;
        CardEntity departmentEntity = null;
        CardEntity positionEntity = null;
        CardEntity phoneEntity = null;
        CardEntity addressEntity = null;

        ArrayList<CardEntity> cardEntities = null;

        CardEntityFactory cardEntityFactory = new CardEntityFactory();

        if (cursor.moveToFirst()) {
            do {
                nameEntity = cardEntityFactory.createCarEntity(CardEntityFactory.CARD_ENTITY_TYPE.NAME, cursor.getString(0));
                companyEntity = cardEntityFactory.createCarEntity(CardEntityFactory.CARD_ENTITY_TYPE.COMPANY, cursor.getString(1));
                departmentEntity = cardEntityFactory.createCarEntity(CardEntityFactory.CARD_ENTITY_TYPE.DEPARTMENT, cursor.getString(2));
                positionEntity = cardEntityFactory.createCarEntity(CardEntityFactory.CARD_ENTITY_TYPE.POSITION, cursor.getString(3));
                phoneEntity = cardEntityFactory.createCarEntity(CardEntityFactory.CARD_ENTITY_TYPE.PHONE, cursor.getString(4));
                addressEntity = cardEntityFactory.createCarEntity(CardEntityFactory.CARD_ENTITY_TYPE.ADDRESS, cursor.getString(5));

                cardEntities = new ArrayList<CardEntity>();
                cardEntities.add(nameEntity);
                cardEntities.add(companyEntity);
                cardEntities.add(departmentEntity);
                cardEntities.add(positionEntity);
                cardEntities.add(phoneEntity);
                cardEntities.add(addressEntity);

                card = new Card(cardEntities);

                card.setIsKeywordOfCardEntity(cardEntityType);

                cards.add(card);

            } while (cursor.moveToNext());
        }

        return cards;
    }

    public void deleteAllDB() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(CARDS_VIRTUAL_TABLE, null, null);
    }
}
