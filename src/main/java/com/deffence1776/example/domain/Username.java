package com.deffence1776.example.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Username {
    @Length(min = 5, max = 12)
    @NotNull
    String value;

    public Username(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
