package com.restful.booker.crudtest;

import com.restful.booker.model.BookingDates;
import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BookingCRUDTest extends TestBase {
    static ValidatableResponse response;
    BookingPojo bookingPojo = new BookingPojo();


    @Test
    public void test01_GetBooking() {
        response = given()
                .when()
                .get("/1")
                .then().log().all().statusCode(200);
    }

    @Test
    public void test02_CreateBooking() {

        bookingPojo.setFirstname("Prime" + TestUtils.getRandomValue());
        bookingPojo.setLastname("Ram" + TestUtils.getRandomValue());
        bookingPojo.setTotalPrice(155);
        bookingPojo.setDepositPaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckIn("2024-10-01");
        bookingDates.setCheckOut("2024-11-02");
        bookingPojo.setBookingDates(bookingDates);
        bookingPojo.setAdditionalNeeds("All-Inclusive");

        response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(bookingPojo)
                .post()
                .then().log().all().statusCode(200);
    }

    @Test
    public void test003_PutBooking() {
        bookingPojo.setFirstname("PrimeT" + TestUtils.getRandomValue());
        bookingPojo.setLastname("Testings" + TestUtils.getRandomValue());
        bookingPojo.setTotalPrice(155);
        bookingPojo.setDepositPaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckIn("2024-10-01");
        bookingDates.setCheckOut("2024-11-02");
        bookingPojo.setBookingDates(bookingDates);
        bookingPojo.setAdditionalNeeds("All-Inclusive");
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(bookingPojo)
                .put("/2671");
        response.then().log().all().statusCode(200);

    }

    @Test
    public void test004_PartialUpdateBooking() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("Testing" + TestUtils.getRandomValue());
        bookingPojo.setLastname("Prime" + TestUtils.getRandomValue());
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(bookingPojo)
                .patch("/2671");
        response.then().log().all().statusCode(200);
    }
}
