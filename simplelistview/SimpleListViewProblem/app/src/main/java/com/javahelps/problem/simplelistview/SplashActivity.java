package com.javahelps.problem.simplelistview;

import android.accounts.NetworkErrorException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.javahelps.problem.simplelistview.quote.GeneratorListener;
import com.javahelps.problem.simplelistview.quote.Quote;
import com.javahelps.problem.simplelistview.quote.QuotesGenerator;

import java.util.ArrayList;
import java.util.List;


public class SplashActivity extends ActionBarActivity implements GeneratorListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        QuotesGenerator generator = new QuotesGenerator();
        generator.setGeneratorListener(this);
        try {
            generator.start(this);
        } catch (NetworkErrorException e) {
            Toast.makeText(this, "No network connection.", Toast.LENGTH_SHORT).show();
            finish();   // Close the application
        }
    }

    @Override
    public void onSucceed(List<Quote> quotes) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("QUOTES", (ArrayList) quotes);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailed(Exception ex) {
        Toast.makeText(this, "Failed to download the quotes.", Toast.LENGTH_SHORT).show();
        finish();   // Close the application
    }
}
