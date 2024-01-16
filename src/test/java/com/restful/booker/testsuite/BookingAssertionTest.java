package com.restful.booker.testsuite;

import com.restful.booker.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class BookingAssertionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
        RestAssured.basePath = "booking";

        response = given()
                .when()
                .get("/1")
                .then().statusCode(200);
    }
    @Test
    public void test01(){
        response.body("firstname",equalTo("Mary"));
    }
    @Test
    public void test02(){
        response.body("lastname", equalTo("Jackson"));
    }
    @Test
    public void test03(){
        response.body("totalPrice", equalTo(268));
    }
    @Test
    public void test04(){
        response.body("depositPaid", equalTo(false));
    }
    @Test
    public void test05(){
        response.body("bookingDates.checkIn", equalTo("2021-08-27"));
    }
    @Test
    public void test06(){
        response.body("bookingDates.checkOut", equalTo("2023-03-31"));
    }
    @Test
    public void test07(){
        response.body("additionalNeeds", equalTo("Breakfast"));
    }

}
