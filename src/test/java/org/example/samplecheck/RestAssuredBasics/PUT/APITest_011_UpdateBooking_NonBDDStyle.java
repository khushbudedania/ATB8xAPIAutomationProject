package org.example.samplecheck.RestAssuredBasics.PUT;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest_011_UpdateBooking_NonBDDStyle {
    @Description("Tc_1 Verify the PUT Request for the Booking")
    @Test

    public void  test_update_Booking()
    {
        String Token = "4817972b7d2b32e";
        String Booking_ID = "526";

        String Payload = "{\n" +
                "    \"firstname\" : \"KHUSHBU\",\n" +
                "    \"lastname\" : \"Dedania\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";



        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com/");
        r.basePath("/booking/"+ Booking_ID);
        r.contentType(ContentType.JSON)  ;
        r.cookie("token",Token);
        r.body(Payload).log().all();


        Response response = r.when().put();

        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);
    }
}

