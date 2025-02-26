package org.example.samplecheck.Assertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.hamcrest.Matchers;
import static org.assertj.core.api.Assertions.*;

public class APOITest_030_Assertion {

        //  POST - Create -> Verify the Response

        RequestSpecification requestSpecification;
        ValidatableResponse validatableResponse;
        Response response;
        String token;
        Integer bookingId;

        @Test
        public void test_post() {
            String payload_POST = "{\n" +
                    "    \"firstname\" : \"Pramod\",\n" +
                    "    \"lastname\" : \"Dutta\",\n" +
                    "    \"totalprice\" : 111,\n" +
                    "    \"depositpaid\" : false,\n" +
                    "    \"bookingdates\" : {\n" +
                    "        \"checkin\" : \"2024-01-01\",\n" +
                    "        \"checkout\" : \"2024-01-01\"\n" +
                    "    },\n" +
                    "    \"additionalneeds\" : \"Lunch\"\n" +
                    "}";


            requestSpecification = RestAssured.given();
            requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
            requestSpecification.basePath("/booking");
            requestSpecification.contentType(ContentType.JSON);
            requestSpecification.body(payload_POST).log().all();

            Response response = requestSpecification.when().post();

            // Get Validatable response to perform validation
            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(200);



            // Validatable Response - I - Hamcrest - Rest Assured
            // Rest Assured Default - Hamcrest
            // import org.hamcrest.Matchers;

            validatableResponse.body("booking.firstname",Matchers.equalTo("Pramod"));
            validatableResponse.body("booking.lastname",Matchers.equalTo("Dutta"));
            validatableResponse.body("booking.depositpaid",Matchers.equalTo(false));
            validatableResponse.body("bookingid",Matchers.notNullValue());

            //Extract the data from the response

            bookingId = response.then().extract().path("bookingid");
            String firstname = response.then().extract().path("booking.firstname");
            String lastname = response.then().extract().path("booking.lastname");

            //TestNG

            Assert.assertNotNull(bookingId);
            Assert.assertEquals(firstname,"Pramod");
            Assert.assertEquals(lastname,"Dutta");


            // AssertJ( 3rd- Lib to Assertions)

            assertThat(bookingId).isNotNull().isPositive().isNotZero();
            assertThat(firstname).isEqualTo("Pramod").isNotNull().isNotBlank().isNotEmpty().isAlphanumeric();

            String s = ""; //Empty
            String s2 = " "; //Blank
        }
    }
// 1) Validatable Response
// Rest Assured Default - Hamcrest
//import org.hamcrest.Matchers;

//2) TestNG Assertions
// SoftAssert vs
// HardAssert -
// This means that if any assertion fails,
// the remaining statements in that test method will not be executed.

//3) AssertJ( 3rd- Lib to Assertions)
//import static org.assertj.core.api.Assertions.*;

//extract data can do two way.
//1) json path
//2) extract() fun.