package org.example.samplecheck.RestAssuredBasics.GET;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITest005_GET_TestNG {

    @Test
    public void Test_Get_Request()
    {
        RestAssured.
                given().
                   baseUri("https://restful-booker.herokuapp.com").
                   basePath("/booking").
                when().
                    get().
                then().log().all().statusCode(200);



    }
}
