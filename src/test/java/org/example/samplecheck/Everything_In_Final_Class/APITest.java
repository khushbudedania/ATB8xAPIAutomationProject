package org.example.samplecheck.Everything_In_Final_Class;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class APITest {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void test_post()
    {
        Booking booking = new Booking();
        booking.setFirstname("khushbu");
        booking.setLastname("Dedania");
        booking.setTotalprice(1254);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2025-10-04");
        bookingdates.setCheckout("2025-10-08");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("nothing");

        System.out.println(booking);

        Gson gson = new Gson();
        String jsonpayload = gson.toJson(booking);

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonpayload).log().all();

        response = requestSpecification.when().post();
        String jsonResponseString  = response.asString();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = gson.fromJson(jsonResponseString,BookingResponse.class);

        assertThat(bookingResponse.getBookingid()).isNotNull().isNotZero();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("khushbu");


    }
}
