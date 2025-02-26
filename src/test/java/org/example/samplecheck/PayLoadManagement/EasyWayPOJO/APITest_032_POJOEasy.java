package org.example.samplecheck.PayLoadManagement.EasyWayPOJO;

import org.example.samplecheck.PayLoadManagement.EasyWayPOJO.Booking;
import org.example.samplecheck.PayLoadManagement.EasyWayPOJO.BookingDates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest_032_POJOEasy {

        RequestSpecification requestSpecification;
        ValidatableResponse validatableResponse;

        @Test
        public void testPOSTReq() {

            // POJO Class automatically convert into  -> JSON

            Booking booking = new Booking();
            booking.setFirstname("Pramod");
            booking.setLastname("Dutta");
            booking.setTotalprice(112);
            booking.setDepositpaid(true);

            BookingDates bookingdates = new BookingDates();
            bookingdates.setCheckin("2024-02-01");
            bookingdates.setCheckout("2024-02-01");

            booking.setBookingdates(bookingdates);
            booking.setAdditionalneeds("Breakfast");

            System.out.println(booking);


            requestSpecification = RestAssured.given();
            requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
            requestSpecification.basePath("booking");
            requestSpecification.contentType(ContentType.JSON);
            requestSpecification.body(booking).log().all();

            Response response = requestSpecification.when().post();

            // Get Validatable response to perform validation
            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(200);

            Integer bookingId = response.then().extract().path("bookingid");
            System.out.println("Your Booking Id is -> " +  bookingId);
        }
    }

