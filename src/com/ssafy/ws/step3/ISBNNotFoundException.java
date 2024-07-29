package com.ssafy.ws.step3;

public class ISBNNotFoundException extends Exception {
    private String isbn;

    public ISBNNotFoundException(String isbn) {
        super("수량이 부족합니다.");
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }
}
