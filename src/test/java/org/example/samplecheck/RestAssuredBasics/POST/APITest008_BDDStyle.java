package org.example.samplecheck.RestAssuredBasics.POST;

import io.qameta.allure.Description;
import io.qameta.allure.internal.shadowed.jackson.core.util.RequestPayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class APITest008_BDDStyle {


    @Description("tc- 1 BDDStyle Auth Request Test Case")
    @Test
    public void Test_BDDStyle() {

      /*  String Payload = { "username" : "admin";
                "password" : "password123"
};*/



        RestAssured
                .given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .basePath("/auth")
                    .contentType("application/json")
               // .log().all().body()
                .when()
                    .log().all().post()
                .then()
                    .log().all()
                    .statusCode(200);


    }
}