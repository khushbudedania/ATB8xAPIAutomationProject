package org.example.samplecheck.RestAssuredBasics.GET;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITest006_GET_BDDStyle {

    @Test
    public  void Test_GET_Request_Positive()
    {
        String pincode = "362630";
        RestAssured
              .given()
                   .baseUri("https://api.zippopotam.us")
                    .basePath("/IN/" +pincode)
                .when()
                    .log().all().get()
                .then()
                    .log().all()
                    .statusCode(200);
    }

    @Test
    public  void Test_GET_Request_Negative()
    {
        String pincode = "-1";
        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/" +pincode)
                .when()
                .log().all().get()
                .then()
                .log().all()
                .statusCode(200);
    }

}
