package org.example.samplecheck.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class APITest_014_Scenario2 {
         //Create Booking (POST)
         //Verify GET(GET)

          RequestSpecification requestSpecification;
          Response response;
          ValidatableResponse validatableResponse;

          String bookingid;


          public String Createbooking()
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
              String firstname = response.jsonPath().getString("booking.firstname");

              System.out.println("BookingId = " +bookingid);


              assertThat(firstname).isEqualTo("Jim");

              return bookingid;
          }
          @Description("Verify the Booking by GET Request")
          @Test(priority = 1)
          public void test_Get_Booking()
          {
              bookingid = Createbooking();

              requestSpecification = RestAssured.given();
              requestSpecification.baseUri("https://restful-booker.herokuapp.com");
              requestSpecification.basePath("/booking/"+ bookingid);
              requestSpecification.contentType(ContentType.JSON);

              response = requestSpecification.when().log().all().get();

              validatableResponse = response.then().log().all();
              validatableResponse.statusCode(200);
          }

}
