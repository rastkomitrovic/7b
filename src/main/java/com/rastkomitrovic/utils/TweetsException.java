package com.rastkomitrovic.utils;

import com.rastkomitrovic.model.Error;

public class TweetsException extends Exception {

    private final Error error;

    public TweetsException(Error error) {
        super();
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}
