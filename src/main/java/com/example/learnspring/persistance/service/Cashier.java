package com.example.learnspring.persistance.service;

import java.util.List;

public interface Cashier {

    void checkout(final List<String> isbns,
                  final String username);
}
