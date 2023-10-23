package com.example.learnspring.persistance.service.imp;

import com.example.learnspring.persistance.service.BookShop;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Component
public class TransactionalJdbcBookShop extends JdbcDaoSupport implements BookShop {

    private final DataSourceTransactionManager dataSourceTransactionManager;
    private final TransactionTemplate transactionTemplate;

    public TransactionalJdbcBookShop(final DataSourceTransactionManager platformTransactionManager,
                                     final DataSource dataSource,
                                     final TransactionTemplate transactionTemplate) {
        this.dataSourceTransactionManager = platformTransactionManager;
        this.transactionTemplate = transactionTemplate;
        setDataSource(dataSource);
    }

    @Override
    public void purchase(String isbn, String username) {
        final DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        final TransactionStatus status = dataSourceTransactionManager.getTransaction(def);

        try {

            var price = getJdbcTemplate().queryForObject(PRICE_SQL, Integer.class, isbn);
            getJdbcTemplate().update(STOCK_SQL, isbn);
            getJdbcTemplate().update(ACCOUNT_SQL, price, username);

            dataSourceTransactionManager.commit(status);

        } catch (final DataAccessException e) {
            dataSourceTransactionManager.rollback(status);
            throw e;
        }

    }


    public void purchaseWithTransactionTemplate(String isbn, String username) {

        transactionTemplate.executeWithoutResult((status) -> {

            var price = getJdbcTemplate().queryForObject(PRICE_SQL, Integer.class, isbn);
            getJdbcTemplate().update(STOCK_SQL, isbn);
            getJdbcTemplate().update(ACCOUNT_SQL, price, username);
        });

    }


}
