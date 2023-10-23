package com.example.learnspring.persistance.service.imp;

import com.example.learnspring.persistance.service.BookShop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

//Basic JDBC implementation to better understand transaction management

@Component
@Slf4j
public class JdbcBookShop implements BookShop {


    private final DataSource dataSource;

    public JdbcBookShop(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void purchase(final String isbn, final String username) {

        try (var con = dataSource.getConnection()) {

            try {

                con.setAutoCommit(false);
                int price = 0;


                // Get book price
                try (var stmt1 = con.prepareStatement(PRICE_SQL)) {

                    log.info("Get book price");

                    stmt1.setString(1, isbn);

                    try (var rs = stmt1.executeQuery()) {
                        rs.next();
                        price = rs.getInt("PRICE");
                        log.info("Book price is {}", price);
                    }
                }

                // Update stock of the book by -1
                try (var stmt2 = con.prepareStatement(STOCK_SQL)) {

                    log.info("Update stock of the book by -1");

                    stmt2.setString(1, isbn);
                    stmt2.executeUpdate();
                }

                // Get money from account
                try (var stmt3 = con.prepareStatement(ACCOUNT_SQL)) {

                    log.info("Get money from user account for purchased book");

                    stmt3.setInt(1, price);
                    stmt3.setString(2, username);
                    stmt3.executeUpdate();
                }
                con.commit();

            } catch (final SQLException e) {
                con.rollback();
                throw e;
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
