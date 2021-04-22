package com.example.quotescreator;

public class Quotes {

    private String Quotes_text;
    private String author_name;

    public Quotes(String quotes_text, String author_name) {
        this.Quotes_text = quotes_text;
        this.author_name=author_name;
    }

    public String getQuotes_text() {
        return Quotes_text;
    }

    public String getAuthor_name() {
        return author_name;
    }
}
