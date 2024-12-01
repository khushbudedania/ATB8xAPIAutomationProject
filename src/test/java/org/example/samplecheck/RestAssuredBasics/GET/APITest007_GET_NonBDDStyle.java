package org.example.samplecheck.RestAssuredBasics.GET;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest007_GET_NonBDDStyle {
     static RequestSpecification r = RestAssured.given();

     @Description("TC-1 NonBDDStyleGET_Positive Test case")
     @Test
    public void test_NonBDDStyleGET_Positive()
    {
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/362630");
        r.when().log().all().get();
        r.then().log().all().statusCode(200);

    }
    @Description("TC-2 NonBDDStyleGET_Negative Test case")
    @Test
    public void test_NonBDDStyleGET_Negative()
    {
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/-1");
        r.when().log().all().get();
        r.then().log().all().statusCode(200);

    }
}
