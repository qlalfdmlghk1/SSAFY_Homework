package com.ssafy.ws.step3;

import java.util.List;

public interface IBookManager {
    void add(Book book);
    void remove(String isbn);
    List<Book> getList();
    Book searchByIsbn(String isbn) throws ISBNNotFoundException;
    List<Book> searchByTitle(String title);
    List<Magazine> getMagazines();
    List<Book> getBooks();
    int getTotalPrice();
    double getPriceAvg();
    void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException;
    void buy(String isbn, int quantity) throws ISBNNotFoundException;
    void saveData();
    void loadData();
}
