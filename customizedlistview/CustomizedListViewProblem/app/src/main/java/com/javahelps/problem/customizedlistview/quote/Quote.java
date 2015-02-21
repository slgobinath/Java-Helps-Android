package com.javahelps.problem.customizedlistview.quote;

import java.io.Serializable;

/**
 * Created by L.Gobinath.
 */
public class Quote implements Serializable {
    private static final int PREVIEW_LENGTH = 25;
    private String quote;
    private String author;

    /**
     * Returns the complete quote.
     *
     * @return The complete quote
     */
    public String getQuote() {
        return quote;
    }

    /**
     * Set the quote.
     *
     * @param quote The quote.
     */
    public void setQuote(String quote) {
        this.quote = quote.trim();
    }

    /**
     * Returns the author's name.
     *
     * @return The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the author's name.
     *
     * @param author The author of this quote
     */
    public void setAuthor(String author) {
        this.author = author.trim();
    }

    /**
     * Return a formatted preview with25 characters.
     *
     * @return A preview of the code
     */
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
