package com.example.learnspring;

import com.example.learnspring.aspect.logic.ArithmeticCalculator;
import com.example.learnspring.aspect.logic.UnitCalculator;
import com.example.learnspring.aspect.logic.complex.ComplexCalculator;
import com.example.learnspring.comunication.EventPublisher;
import com.example.learnspring.model.Dog;
import com.example.learnspring.persistance.service.imp.BookShopCashier;
import com.example.learnspring.persistance.service.imp.JdbcBookShop;
import com.example.learnspring.persistance.service.imp.JdbcBookShopWithAnnotations;
import com.example.learnspring.persistance.service.imp.TransactionalJdbcBookShop;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class LearnSpringApplication implements CommandLineRunner {

    private final ArithmeticCalculator arithmeticCalculator;

    private final UnitCalculator unitCalculator;

    private final ComplexCalculator complexCalculator;

    private final EventPublisher eventPublisher;

    private final Dog dog;

    private final JdbcBookShop jdbcBookShop;

    private final TransactionalJdbcBookShop transactionalJdbcBookShop;

    private final JdbcBookShopWithAnnotations jdbcBookShopWithAnnotations;

    private final BookShopCashier bookShopCashier;

    public LearnSpringApplication(final ArithmeticCalculator arithmeticCalculator,
                                  final UnitCalculator unitCalculator,
                                  final ComplexCalculator complexCalculator,
                                  final EventPublisher eventPublisher,
                                  final Dog dog,
                                  final JdbcBookShop jdbcBookShop,
                                  final TransactionalJdbcBookShop transactionalJdbcBookShop,
                                  final JdbcBookShopWithAnnotations jdbcBookShopWithAnnotations,
                                  final BookShopCashier bookShopCashier) {
        this.arithmeticCalculator = arithmeticCalculator;
        this.unitCalculator = unitCalculator;
        this.complexCalculator = complexCalculator;
        this.eventPublisher = eventPublisher;
        this.dog = dog;
        this.jdbcBookShop = jdbcBookShop;
        this.transactionalJdbcBookShop = transactionalJdbcBookShop;
        this.jdbcBookShopWithAnnotations = jdbcBookShopWithAnnotations;
        this.bookShopCashier = bookShopCashier;
    }

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        arithmeticCalculator.add(1, 2);
//        arithmeticCalculator.add(2, 3);
//        arithmeticCalculator.add(4, 5);
//        arithmeticCalculator.add(5, 6);
//
//        unitCalculator.kilogramToPound(10);
//        unitCalculator.kilometerToMile(10);
//
//        var maxCalculator = ((MaxCalculator) arithmeticCalculator);
//
//        System.out.println("Max value = " + maxCalculator.max(2, 3));
//
//        var minCalculator = ((MinCalculator) arithmeticCalculator);
//
//        System.out.println("Min value = " + minCalculator.min(2, 3));
//
//
//        var arithmenticCounter = (Counter) arithmeticCalculator;
//        System.out.println("ArithmeticCalculator nr of invocation  = " + arithmenticCounter.getCount());
//
//        var unitCounter = (Counter) unitCalculator;
//        System.out.println("UnitCalculator nr of invocation = " + unitCounter.getCount());
//
//
//        complexCalculator.add(new Complex(1, 2), new Complex(2, 3));
//        complexCalculator.add(new Complex(5, 8), new Complex(2, 3));
//
//
//        eventPublisher.checkout();
//
//        dog.spek("to uppercase");


        System.out.println("Transaction management segment");

//        jdbcBookShop.purchase("0001", "user1");

//        transactionalJdbcBookShop.purchase("0001", "user1");

//        transactionalJdbcBookShop.purchaseWithTransactionTemplate("0001", "user1");

//        jdbcBookShopWithAnnotations.purchase("0001", "user1");

        final List<String> isbnList = List.of("0001", "0002");

        bookShopCashier.checkout(isbnList, "user1");

    }
}
