package org.example.samplecheck.RestAssuredBasics.PATCH;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest_012_UpdateBooking {

        @Description("Tc_1 Verify the PATCH Request for the Booking")
        @Test

        public void  test_update_Booking()
        {
            String Token = "7f5f3f59da00e83";
            String Booking_ID = "774";

            String Payload = "{\n" +
                    "    \"firstname\" : \"chirag\",\n" +
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


            Response response = r.when().patch();

            ValidatableResponse validatableResponse = response.then().log().all();
            validatableResponse.statusCode(200);
        }
    }


