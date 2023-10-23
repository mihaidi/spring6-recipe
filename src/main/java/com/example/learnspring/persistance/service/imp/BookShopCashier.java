package com.example.learnspring.persistance.service.imp;

import com.example.learnspring.persistance.service.BookShop;
import com.example.learnspring.persistance.service.Cashier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BookShopCashier implements Cashier {

    private final BookShop bookShop;

    public BookShopCashier(final @Qualifier("jdbcBookShopWithAnnotations") BookShop bookShop) {
        this.bookShop = bookShop;
    }

    @Override
    @Transactional
    public void checkout(List<String> isbns, String username) {

        isbns.forEach(isbn -> bookShop.purchase(isbn, username));
    }
}
