package org.spring.micro.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * App.class, is identical in all three projects (customer, product, order), 
 * because it only works as an initiator to Spring Boot - as defined by @SpringBootApplication annotation: 
 * - rising a Spring context;
 * - and the embedded server.
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
