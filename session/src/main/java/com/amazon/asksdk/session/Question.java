package com.amazon.asksdk.session;

public class Question {

    private final String id;
    private final String text;

    public Question(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String id() {
        return id;
    }

    public String text() {
        return text;
    }

}
