package org.example.samplecheck.PayLoadManagement.EasyWayPOJO;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class APITest_033_Ser_Des {

        RequestSpecification requestSpecification;
        ValidatableResponse validatableResponse;

        @Test
        public void testPositive(){

            // Step1 - POST
            // URL -> Base URI + base Path
            // HEADER
            // BODY
            // Auth - NO


            // Step 2
            // prepare the Payload ( Object -> JSON String)
            // send the request

            //Step 3
            // Validate Response ( JSON String -> Object)
            // FirstName,
            // Status Code
            // Time Response



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

            System.out.println(booking);//booking is a object you cannot send client to server directly. you can convert into json.
            //1) either you add annotation.
            //2) create an object of Gson.


            Gson gson = new Gson();
            // Object -> JSON String (GSON) == Ser
            String jsonStringBooking = gson.toJson(booking);
            System.out.println(jsonStringBooking);


            requestSpecification = RestAssured.given();
            requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
            requestSpecification.basePath("booking");
            requestSpecification.contentType(ContentType.JSON);
            requestSpecification.body(jsonStringBooking).log().all();

            Response response = requestSpecification.when().post();
            String jsonResponseString  = response.asString();

            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(200);

            //How to verify Response??
            //Case-1 Response is small then these two are use.
            //1)extract()
            //2)jsonPath().getString()


            //Case-2- Response Complex JSON,Huge JSON
            // String -> Object == De Ser(you need to create new class BookingResponse class )

            BookingResponce bookingResponce = gson.fromJson(jsonResponseString, BookingResponce.class);

            assertThat(bookingResponce.getBookingid()).isNotZero().isNotNull();
            assertThat(bookingResponce.getBooking().getFirstname()).isEqualTo("Pramod").isNotNull().isNotEmpty();
        }
    }

