package com.example.learnspring.persistance.service.imp;

import com.example.learnspring.persistance.service.BookShop;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Component(value = "jdbcBookShopWithAnnotations")
public class JdbcBookShopWithAnnotations extends JdbcDaoSupport implements BookShop {

    public JdbcBookShopWithAnnotations(final DataSource dataSource) {
        setDataSource(dataSource);
    }


    @Override
    @Transactional
    public void purchase(String isbn, String username) {

        var price = getJdbcTemplate().queryForObject(PRICE_SQL, Integer.class, isbn);
        getJdbcTemplate().update(STOCK_SQL, isbn);
        getJdbcTemplate().update(ACCOUNT_SQL, price, username);
    }
}
