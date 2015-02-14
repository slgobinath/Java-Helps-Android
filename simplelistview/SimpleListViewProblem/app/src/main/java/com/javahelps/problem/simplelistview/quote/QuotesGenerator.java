package com.javahelps.problem.simplelistview.quote;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by L.Gobinath.
 * Add the following permissions in the manifest file.
 * <uses-permission android:name="android.permission.INTERNET" />
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 */
public class QuotesGenerator {
    /**
     * URL to connect.
     */
    private static final String QUOTES_URL = "http://quotes4all.net/rss/040110110/quotes.xml";
    /**
     * Listener to push the results, once the download is completed.
     */
    private GeneratorListener listener;
    /**
     * Used to store any Exception received at the runtime.
     */
    private Exception exception;


    /**
     * Start the generator to download and build the quotes.
     */
    public void start(Context context) throws NetworkErrorException {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadManager().execute(QUOTES_URL);
        } else {
            throw new NetworkErrorException("Network not found.");
        }

    }

    /**
     * Set a GeneratorListener, which can be used to receive the results.
     *
     * @param listener Use to receive the quotes.
     */
    public void setGeneratorListener(GeneratorListener listener) {
        this.listener = listener;
    }

    /**
     * Given a URL, establishes an HttpUrlConnection and retrieves the web page content as a InputStream, which it returns as a string.
     */
    private List<Quote> downloadUrl(String urlParam) throws IOException, XmlPullParserException {
        InputStream is = null;
        try {
            URL url = new URL(urlParam);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("Quotes_Generator", "The response is: " + response);
            if (response == 200) {
                // OK
                is = conn.getInputStream();
                return readXML(is);
            }
            // Makes sure that the InputStream is closed after the app is finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return null;
    }

    /**
     * Read an InputStream and returns the Quotes.
     *
     * @param stream The InputStream received from URL.
     * @return A List of quotes.
     */
    private List<Quote> readXML(InputStream stream) throws XmlPullParserException, IOException {
        List<Quote> quotes = new ArrayList<>();
        boolean isInsideItem = false;
        boolean isInsideAuthor = false;
        boolean isInsideQuote = false;
        Quote quote = null;

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(stream, "UTF-8");

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                String tag = parser.getName();
                if ("item".equals(tag)) {
                    isInsideItem = true;
                    quote = new Quote();    // Create a new quote
                } else if (isInsideItem && "title".equals(tag)) {
                    isInsideAuthor = true;
                } else if (isInsideItem && "description".equals(tag)) {
                    isInsideQuote = true;
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                String tag = parser.getName();
                if ("item".equals(tag)) {
                    isInsideItem = false;
                    quotes.add(quote);  // Add the quote to the list
                } else if (isInsideItem && "title".equals(tag)) {
                    isInsideAuthor = false;
                } else if (isInsideItem && "description".equals(tag)) {
                    isInsideQuote = false;
                }
            } else if (eventType == XmlPullParser.TEXT) {
                String text = parser.getText();
                if (isInsideAuthor) {
                    quote.setAuthor(text);
                } else if (isInsideQuote) {
                    quote.setQuote(text);
                }
            }
            eventType = parser.next();
        }
        return quotes;
    }

    /**
     * Separate thread to download from URL.
     */
    private class DownloadManager extends AsyncTask<String, Void, List<Quote>> {
        @Override
        protected List<Quote> doInBackground(String... urls) {
            String url = urls[0];
            List<Quote> result = null;
            try {
                result = downloadUrl(url);
            } catch (IOException e) {
                QuotesGenerator.this.exception = e;
            } catch (XmlPullParserException e) {
                QuotesGenerator.this.exception = e;
            }
            return result;
        }


        @Override
        protected void onPostExecute(List<Quote> result) {
            if (listener != null) {
                if (result != null) {
                    listener.onSucceed(result);
                } else {
                    listener.onFailed(exception);
                }
            }
        }
    }
}
