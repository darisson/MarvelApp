package com.android.github.marvelapp.marvelapp.data.api.exception;

public class MarvelApiException extends Throwable {
    private int code;
    private String status;

    public MarvelApiException(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public MarvelApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
