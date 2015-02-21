package com.javahelps.problem.customizedlistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.javahelps.problem.customizedlistview.quote.Quote;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private List<Quote> quotes;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.listView = (ListView) findViewById(R.id.listView);
        receiveQuotes();

        // TODO: 2. Replace the ArrayAdapter by your CustomizedArrayAdapter
        ArrayAdapter<Quote> adapter = new ArrayAdapter<Quote>(this, android.R.layout.simple_list_item_1, quotes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showQuote(position);
            }
        });
    }

    private void showQuote(int index) {
        Quote quote = quotes.get(index);
        Toast.makeText(this, quote.getQuote(), Toast.LENGTH_LONG).show();
    }

    private void receiveQuotes() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            this.quotes = (ArrayList) bundle.get("QUOTES");
        } else {
            this.quotes = new ArrayList<>();
        }
    }

    // TODO: 1. Create a sub class of ArrayAdapter<Quote>
}
