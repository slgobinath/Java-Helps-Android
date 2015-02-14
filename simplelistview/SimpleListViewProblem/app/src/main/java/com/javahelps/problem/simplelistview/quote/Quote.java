package com.javahelps.problem.simplelistview.quote;

import java.io.Serializable;

/**
 * Created by L.Gobinath.
 */
public class Quote implements Serializable {
    private static final int PREVIEW_LENGTH = 20;
    private String quote;
    private String author;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author.trim();
    }

    public String getPreview() {
        String preview;
        if (quote.length() > PREVIEW_LENGTH) {
            preview = quote.substring(0, PREVIEW_LENGTH) + "...";
        } else {
            preview = quote;
        }
        return preview;
    }

    @Override
    public String toString() {
        return getPreview();
    }
}
