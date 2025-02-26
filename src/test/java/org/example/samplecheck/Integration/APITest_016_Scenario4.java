package org.example.samplecheck.Integration;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest_016_Scenario4 {
    //Create Booking.(POST)
    //Create Token.(POST)
    //Delete Booking.(DELETE)
    //Try to Get Deleted Booking Id.(GET)

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    String token;
    String bookingid;

    public String gettoken()
    {
        String Payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(Payload);

        response = requestSpecification.when().log().all().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        token = response.jsonPath().getString("token");
        System.out.println("Token = " +token);

        return token;

    }

    public String getbookingid()
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
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(Payload);

        response = requestSpecification.when().log().all().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        bookingid = response.jsonPath().getString("bookingid");
        System.out.println("Bookingid = " +bookingid);

        return bookingid;
    }

    @Description("Verify that the Booking is Deleted Successfully.")
    @Test(priority = 1)
    public void test_Delete_Booking()
    {
        token = gettoken();
        bookingid = getbookingid();
        System.out.println("Token = "+token);
        System.out.println("Bookingid = "+bookingid);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" +bookingid);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        response = requestSpecification.when().delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
    }

    @Description("Try to Fetch the Already Deleted booking./Verify ID dosen't exist.")
    @Test(priority = 2)
    public void test_Get_Booking()
    {
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingid);
        requestSpecification.contentType(ContentType.JSON);

        response = requestSpecification.when().log().all().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);

        //bookingid = response.jsonPath().getString("bookingid");

    }


}
