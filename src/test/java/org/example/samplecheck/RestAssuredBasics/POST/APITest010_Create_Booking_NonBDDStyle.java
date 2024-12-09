package org.example.samplecheck.RestAssuredBasics.POST;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest010_Create_Booking_NonBDDStyle {

    @Description("tc-1 verify the Create Booking Test Case")
    @Test
    public  void test_CreateBooking()
    {
        String Payload = "'{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}'";

        RequestSpecification r = RestAssured.given();
                r.baseUri("https://restful-booker.herokuapp.com");
                r.basePath("/booking");
                r.contentType("application/json");
                r.body(Payload);
                r.when().log().all().post();
                r.then().log().all().statusCode(200);

    }
}
