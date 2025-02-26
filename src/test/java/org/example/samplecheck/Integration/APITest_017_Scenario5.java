package org.example.samplecheck.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest_017_Scenario5
{
    //Create Booking.(POST)
    //Get Booking.(GET)

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    String bookingId;

    public String createbooking()
    {
        String Payload = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.body(Payload);
        requestSpecification.contentType(ContentType.JSON);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


        bookingId = response.jsonPath().getString("bookingid");//extract bookingid from the response
        System.out.println("BookingId = " +bookingId);

        return bookingId;
    }

    @Test
    public void test_get()
    {
         bookingId = createbooking();

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingId);
        requestSpecification.contentType(ContentType.JSON);

        response = requestSpecification.when().log().all().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        bookingId = response.jsonPath().getString("bookingid");//extract bookingid from the response
        System.out.println("BookingId = " +bookingId);
    }

}
