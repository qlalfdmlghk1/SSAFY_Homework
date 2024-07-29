package com.ssafy.ws.step3;

public class BookTest {
    public static void main(String[] args) {
        IBookManager manager = BookManagerImpl.getInstance();

        Book b1 = new Book("21424","Java Pro","김하나","jaen.kr",15000,"Java 기본",10);
		Book b2 = new Book("21425","Java Pro2","김하나","jaen.kr",25000,"Java 응용",20);
		Book b3 = new Book("35355","분석설계","소나무","jaen.kr",30000,"SW 모델링",30);
		Magazine m1 = new Magazine("45678", "월간 알고리즘", "홍길동", "jaen.kr", 10000, "1월 알고리즘", 2021, 1, 40);

        manager.add(b1);
        manager.add(b2);
        manager.add(b3);
        manager.add(m1);

        System.out.println("======= 도서 전체 목록 =======");
        for (Book book : manager.getList()) {
            System.out.println(book);
        }

        try {
            manager.sell("123", 5);
            manager.sell("123", 10);
        } catch (QuantityException | ISBNNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            manager.buy("123", 5);
        } catch (ISBNNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n");
        for (Book book : manager.getList()) {
            System.out.println(book);
        }
        
     // save data
        manager.saveData();
    }
}
