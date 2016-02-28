package com.example.wes.localdbsearch.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wes.localdbsearch.R;
import com.example.wes.localdbsearch.model.Card;
import com.example.wes.localdbsearch.controller.CardEntityFactory;

import java.util.ArrayList;

/**
 * Created by wes on 16. 2. 26.
 */
public class ListViewAdapter extends ArrayAdapter<Card> {
    private Context context;
    private ArrayList<Card> cards;

    public ListViewAdapter(Context context, int resource, ArrayList<Card> cards) {
        super(context, resource, cards);
        this.context = context;
        this.cards = cards;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView textViewOfName = (TextView) rowView.findViewById(R.id.name);
        TextView textViewOfCompany = (TextView) rowView.findViewById(R.id.company);
        TextView textViewOfPosition = (TextView) rowView.findViewById(R.id.position);
        TextView textViewOfSearchColumn = (TextView) rowView.findViewById(R.id.searchColumn);
        TextView textViewOfSearchKeyword = (TextView) rowView.findViewById(R.id.searchKeyword);

        textViewOfName.setText(cards.get(position).getValue(CardEntityFactory.CARD_ENTITY_TYPE.NAME));
        textViewOfCompany.setText(cards.get(position).getValue(CardEntityFactory.CARD_ENTITY_TYPE.COMPANY));
        textViewOfPosition.setText(cards.get(position).getValue(CardEntityFactory.CARD_ENTITY_TYPE.POSITION));
        textViewOfSearchColumn.setText(cards.get(position).getKeywordOfKoreaNameOfType());
        textViewOfSearchKeyword.setText(cards.get(position).getKeywordValue());

        return rowView;
    }
}
