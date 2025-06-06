package com.insta.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class InstagramApiTestConfig {
    protected static RequestSpecification requestSpec;

    @BeforeAll
    public static void setup() {
        Configuration.loadConfig();

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://graph.instagram.com/v18.0")
                .addHeader("Authorization", "Bearer " + Configuration.getProperty("instagram.access.token"))
                .build();

        RestAssured.requestSpecification = requestSpec;
    }
}