package com.example.wes.localdbsearch.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wes.localdbsearch.R;
import com.example.wes.localdbsearch.model.SearchResult;

import java.util.ArrayList;

/**
 * Created by wes on 16. 2. 26.
 */
public class ListViewAdapter extends ArrayAdapter<SearchResult> {
    private Context context;
    private ArrayList<SearchResult> searchResults;

    public ListViewAdapter(Context context, int resource, ArrayList<SearchResult> searchResults) {
        super(context, resource, searchResults);
        this.context = context;
        this.searchResults = searchResults;
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

        textViewOfName.setText(searchResults.get(position).getCard().getName());
        textViewOfCompany.setText(searchResults.get(position).getCard().getCompany());
        textViewOfPosition.setText(searchResults.get(position).getCard().getPosition());
        textViewOfSearchColumn.setText(searchResults.get(position).getSearchColumn());
        textViewOfSearchKeyword.setText(searchResults.get(position).getSearchKeyword());

        return rowView;
    }
}
