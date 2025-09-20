package com.mehedy.journal_app.constants;

public enum Placeholders {
    API_KEY("<apiKey>"),
    CITY("<city>");

    private final String placeholder;
    Placeholders(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getPlaceholder(){
        return placeholder;
    }

}
