package com.example.learnspring.aspect.logic.complex;

public record Complex(int real, int imaginary) {
    @Override
    public String toString() {
        return "( %d + %d )" .formatted(real, imaginary);
    }
}
