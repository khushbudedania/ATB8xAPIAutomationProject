package org.example.samplecheck.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class APITest_013_Scenario1 {
    //Create a Token.(POST)
    //Create a Booking.(POST)
    //Perform a put Request.(PUT)
    //Verify a PUT Sucess by GET Request.(GET)
    //Delete the ID.(DELETE)
    //Verify ID dosen't exist.(GET)

    RequestSpecification requestSpecification;//Instance variable for reuse it.
    Response response;
    ValidatableResponse validatableResponse;

    String token;
    String bookingId;

    public String getToken() {

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\" \n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        token = response.jsonPath().getString("token");//extract the token
        System.out.println("Token = " +token);

        return token;
    }

    public String getbookingId() {

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
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(Payload);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        bookingId = response.jsonPath().getString("bookingid");//extract bookingid from the response
        System.out.println("BookingId = " +bookingId);

        return bookingId;
    }

    @Test(priority = 1)
    public void test_put_Request() {
        token = getToken();
        bookingId = getbookingId();
        System.out.println("Token = " +token);
        System.out.println("BookingId = " +bookingId);

        String Payload = "{\n" +
                "    \"firstname\" : \"myra\",\n" +
                "    \"lastname\" : \"Dedania\",\n" +
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
        requestSpecification.basePath("/booking/" + bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(Payload).log().all();

        response = requestSpecification.when().put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        String firstname = response.jsonPath().getString("firstname");
        String lastname = response.jsonPath().getString("lastname");
        String totalprice = response.jsonPath().getString("totalprice");
        String depositpaid = response.jsonPath().getString("depositpaid");
        String bookingdates = response.jsonPath().getString("bookingdates.checkin");


        assertThat(token).isEqualTo(token);
        assertThat(token).isNotBlank().isNotNull().isNotEmpty();
        assertThat(bookingId).isNotEmpty().isNotNull().isNotBlank();
        assertThat(bookingId).toString();
        assertThat(bookingId).isEqualTo(bookingId);
        assertThat(firstname).isEqualTo("myra");
        assertThat(lastname).isEqualTo("Dedania");
        assertThat(totalprice).isNotEmpty().isNotNull().isNotBlank();
       // assertThat(depositpaid).isEqualTo("false");
        //assertThat(bookingdates).isEqualTo("2026-01-01");



    }

    @Test(priority = 2)
    public void test_get_Request()//after put
    {
       // bookingId = getbookingId();//if we use a getbookingId function here then they generate the new booking id.
        System.out.println(bookingId);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingId);

        response = requestSpecification.when().log().all().get();
        requestSpecification.then().log().all().statusCode(200);

        String firstname = response.jsonPath().getString("firstname");
        System.out.println(firstname);

        Assert.assertEquals(firstname,"myra");
    }

    @Test(priority = 3)
    public  void test_Delete_Booking()
    {
        System.out.println(bookingId);
        System.out.println(token);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        response = requestSpecification.when().delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);



    }

    @Test(priority = 4)
    public void test_get_Request_afterDelete()
    {
        //System.out.println(bookingId);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingId);

        response = requestSpecification.when().log().all().get();
        requestSpecification.then().log().all().statusCode(404);

    }
    }

