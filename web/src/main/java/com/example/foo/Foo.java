package com.example.foo;

import com.foo.BarBean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class Foo {

    final public static String MESSAGE = "Foo weblogic!";

    @Produces
    public BarBean produce() {
        BarBean b = new BarBean();
        b.setMessage(MESSAGE);
        return b;
    }
}
