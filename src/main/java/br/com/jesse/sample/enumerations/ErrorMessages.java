package br.com.jesse.sample.enumerations;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ErrorMessages {

    ER0001, EM0001, EM0002;

    private static final ResourceBundle messagesRB = ResourceBundle.getBundle("br.com.jesse.sample.properties.messages");

    public String getMessage() {
        return messagesRB.getString(this.toString());
    }

}
