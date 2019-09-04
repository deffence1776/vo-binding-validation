package com.deffence1776.example.domain;

import org.hibernate.validator.constraints.Range;

public class Age {
    @Range(min=0,max = 150)
    int value;

    public Age(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
