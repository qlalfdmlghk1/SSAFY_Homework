package com.ssafy.ws.step3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BookManagerImpl implements IBookManager {
    private List<Book> books;
    private static BookManagerImpl instance;
    private static final String DATA_FILE = "book.dat";

    private BookManagerImpl() {
        books = new ArrayList<>();
        loadData();
    }

    public static BookManagerImpl getInstance() {
        if (instance == null) {
            instance = new BookManagerImpl();
        }
        return instance;
    }

    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public void remove(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    @Override
    public List<Book> getList() {
        return books;
    }

    @Override
    public Book searchByIsbn(String isbn) throws ISBNNotFoundException {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new ISBNNotFoundException(isbn));
    }

    @Override
    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().contains(title)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Magazine> getMagazines() {
        List<Magazine> result = new ArrayList<>();
        for (Book book : books) {
            if (book instanceof Magazine) {
                result.add((Magazine) book);
            }
        }
        return result;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (!(book instanceof Magazine)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public int getTotalPrice() {
        int total = 0;
        for (Book book : books) {
            total += book.getPrice();
        }
        return total;
    }

    @Override
    public double getPriceAvg() {
        if (books.isEmpty()) return 0;
        return (double) getTotalPrice() / books.size();
    }

    @Override
    public void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException {
        Book book = searchByIsbn(isbn);
        if (book.getQuantity() < quantity) {
            throw new QuantityException("Not enough quantity in stock");
        }
        book.setQuantity(book.getQuantity() - quantity);
    }

    @Override
    public void buy(String isbn, int quantity) throws ISBNNotFoundException {
        Book book = searchByIsbn(isbn);
        book.setQuantity(book.getQuantity() + quantity);
    }
    
    @Override
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            books = (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
