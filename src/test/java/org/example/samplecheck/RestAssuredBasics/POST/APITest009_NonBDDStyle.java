package org.example.samplecheck.RestAssuredBasics.POST;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest009_NonBDDStyle {
    @Description("tc- 1 NonBDDStyle Auth Request Test Case")
    @Test
    public void Test_NonBDDStyle() {

        String Payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

         RequestSpecification r = RestAssured.given();

                r.baseUri("https://restful-booker.herokuapp.com");
                r.basePath("/auth");
                r.contentType("application/json").log().all();
                r.body(Payload);
                r.when().log().all().post();
                r.then().log().all().statusCode(200);


    }
}



