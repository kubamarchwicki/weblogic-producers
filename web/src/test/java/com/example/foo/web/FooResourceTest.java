package com.example.foo.web;

import com.example.foo.Application;
import com.example.foo.Foo;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URL;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class FooResourceTest {

    @Deployment(testable = false)
    public static WebArchive create() {
        File[] libs = Maven.resolver().loadPomFromFile("pom.xml")
                .resolve("com.example.foo:bean").withoutTransitivity()
                .asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addAsLibraries(libs)
                .addClasses(RestApp.class, FooResource.class)
                .addPackage(Application.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @RunAsClient
    public void should_return_message_from_servlet(@ArquillianResource URL baseUrl) {

        Client client = ClientBuilder.newClient();
        Response response = client.target(baseUrl + "rs/foo")
                .request().get();

        assertThat(response.readEntity(String.class), equalTo(Foo.MESSAGE));
        assertThat(response.getStatus(), equalTo(200));
    }

}
