//package com.insta.tests;
//
//import com.insta.config.InstagramApiTestConfig;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.Test;
//
//import static io.restassured.RestAssured.given;
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class InstagramApiTest extends InstagramApiTestConfig {
//
//    @Test
//    public void testGetUserProfile() {
//        Response response = given()
//                .when()
//                .get("/me?fields=id,username,account_type")
//                .then()
//                .statusCode(200)
//                .extract()
//                .response();
//
//        assertThat(response.jsonPath().getString("username")).isNotEmpty();
//        assertThat(response.jsonPath().getString("id")).isNotEmpty();
//    }
//
//    @Test
//    public void testGetUserMedia() {
//        Response response = given()
//                .when()
//                .get("/me/media?fields=id,caption,media_type,media_url,timestamp")
//                .then()
//                .statusCode(200)
//                .extract()
//                .response();
//
//        assertThat(response.jsonPath().getList("data")).isNotNull();
//    }
//}