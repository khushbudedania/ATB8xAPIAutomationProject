package org.example.samplecheck.PayLoadManagement;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class   APITest_030_HashMap {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    @Test
    public void testPOSTReq() {
        //{
        //            "firstname" : "Jim",
        //                "lastname" : "Brown",
        //                "totalprice" : 111,
        //                "depositpaid" : true,
        //                "bookingdates" : {
        //            "checkin" : "2018-01-01",
        //                    "checkout" : "2019-01-01"
        //        },
        //            "additionalneeds" : "Breakfast"
        //        }


        // JSON -> LinkedHashmap Conversion.

        Map<String, Object> jsonBodyUsingMap = new LinkedHashMap();
        jsonBodyUsingMap.put("firstname", "Jim");
        jsonBodyUsingMap.put("lastname", "brown");
        jsonBodyUsingMap.put("totalprice", 123);
        jsonBodyUsingMap.put("depositpaid", true);


        Map<String, Object> bookingDatesMap = new LinkedHashMap();
        bookingDatesMap.put("checkin", "2018-01-01");
        bookingDatesMap.put("checkout", "2019-01-01");

        jsonBodyUsingMap.put("bookingdates", bookingDatesMap);
        jsonBodyUsingMap.put("additionalneeds", "Breakfast");


        System.out.println(jsonBodyUsingMap);

        //LinkedHashmap -> JSON convert by - GSON/ JackSon library.


        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonBodyUsingMap).log().all();

        Response response = requestSpecification.when().post();

        Integer bookingId = response.then().extract().path("bookingid");

        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        System.out.println("Your Booking Id is -> " + bookingId);
    }
}
//PAYLOAD
//1. String - Hardcoded - Not a Scable Approach - TC < 10

//2.HashMap or LinkedHashMap -> Complex or big payload are very difficult to work with - TC < 100
//i)JSON -> LinkedHashmap Conversion.
//ii)LinkedHashmap -> JSON convert by - GSON/ JackSon library.(you add this lib. to maven dependency).
// they automatically convert into json.

//3. Class & Object -> POJO Classes -> TC > 100
//    1. Ser / De Ser
//    2. GSON or Jackson API

//POJO Class?
//- POJO, which stands for Plain Old Java Object, is a standard Java class.
//- Default Constructor
//- Encapsulation
//    - private,
//    - getter and setter

//**Restrictions**
//POJOs should not extend predefined classes or implement prespecified interface.

//#### Benefits of Using POJOs
//- `Simplicity: POJOs are easy to understand and maintain.`
//- `Reusability: They can be used across different Java programs without restriction.`
//- `Flexibility: POJOs can be easily modified and extended.`
//- Framework Independence.