package com.deffence1776.example.presentation;

import com.deffence1776.example.domain.Age;
import com.deffence1776.example.domain.Username;
import com.deffence1776.example.fw.validations.ValueObject;

import javax.validation.constraints.NotNull;

public class SampleForm {

    @ValueObject(emptyToNull = false)
    Username username;

    @ValueObject(parameterType = Integer.class,emptyToNull = true)
    @NotNull
    Age age;

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }
}
