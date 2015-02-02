package com.example.foo;

import com.foo.BarBean;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Singleton;

@Startup
@Singleton
public class Application {

    @Inject
    BarBean bar;

    @PostConstruct
    public void foo() {
        System.out.println("bar = " + bar.getMessage());
    }

    public String getBarMessage() {
        return bar.getMessage();
    }
}
