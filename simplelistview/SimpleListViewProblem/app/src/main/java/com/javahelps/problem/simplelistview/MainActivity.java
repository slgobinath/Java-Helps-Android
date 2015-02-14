package com.javahelps.problem.simplelistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.javahelps.problem.simplelistview.quote.Quote;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    /**
     * List of Quotes
     */
    private List<Quote> quotes;
    // TODO: 1. Create an instance variable for ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: 2. Using findViewById, find and assign the ListView object to your reference

        // Receive the quotes
        receiveQuotes();

        // TODO: 3. Create an ArrayAdapter<Queue> object using the quotes List

        // TODO: 4. Set the adapter to your ListView

        // TODO: 5. Set OnItemClickListener to the ListView and call the showQuote method with suitable argument
    }

    /**
     * Show a quote at the specified index.
     *
     * @param index The index of the quote
     */
    private void showQuote(int index) {
        Quote quote = quotes.get(index);
        Toast.makeText(this, quote.getQuote(), Toast.LENGTH_LONG).show();
    }

    /**
     * Receive the Quotes.
     */
    private void receiveQuotes() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            this.quotes = (ArrayList) bundle.get("QUOTES");
        } else {
            this.quotes = new ArrayList<>();
        }
    }

}
