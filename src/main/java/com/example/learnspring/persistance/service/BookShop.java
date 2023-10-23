package com.example.learnspring.persistance.service;

public interface BookShop {


    String PRICE_SQL = """
            SELECT PRICE
            FROM BOOK
            WHERE isbn = ?
            """;


    String STOCK_SQL = """
             UPDATE BOOK_STOCK
             SET stock = stock - 1
             WHERE isbn = ?
            """;

    String ACCOUNT_SQL = """
             UPDATE ACCOUNT
             SET balance = balance - ?
             WHERE username = ?
            """;


    void purchase(String isbn, String username);
}
