package com.example.foo;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ApplicationTest {

    @Deployment
    public static WebArchive create() {
        File[] libs = Maven.resolver().loadPomFromFile("pom.xml")
                .resolve("com.example.foo:bean").withoutTransitivity()
                .asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addAsLibraries(libs)
                .addPackage(Application.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    Application app;

    @Test
    public void should_be_foo() {
        assertThat(app.getBarMessage(), equalTo(Foo.MESSAGE));
    }
}
