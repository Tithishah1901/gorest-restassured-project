package com.gorest.crudtest;

import com.gorest.constant.EndPoints;
import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static String name =  "Raj";
    static String email = TestUtils.getRandomValue() + "Raj.123@gmail.com";
    static String gender =  "male";
    static String status =  "active";
    static int userId = 0;


    @Test(priority = 1)
    public void createUser(){

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        ValidatableResponse response = given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 6de6769b5969ee445fd991d290ce0c73e630afe0572b30750e9549e4de521640")
                .when()
                .body(userPojo)
                .post(EndPoints.GET_ALL_USERS)
                .then().log().ifValidationFails().statusCode(201);
         userId = response.extract().path("id");
        System.out.println("userId"+ userId);


    }

    @Test(priority = 2)
    public void getId(){


        ValidatableResponse response = given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 6de6769b5969ee445fd991d290ce0c73e630afe0572b30750e9549e4de521640")
                .pathParam("userId",userId)
                .when()
                .get(EndPoints.GET_USERS_BY_ID)
                 .then().log().all().statusCode(200);

        userId = response.extract().path("id");
        System.out.println("userId"+ userId);


    }

    @Test(priority = 3)
    public void updateUserSuccessfully(){
        //String status = UserCRUDTest.status + "_inactive";

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name + "UpdatedName" + TestUtils.getRandomValue());
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer 82762cec140d3a0daae5082f02ab422d1f76fe0ca507c1c454e7ae136d336231")
                .pathParam("userId",userId)
                .when()
                .body(userPojo)
                .put(EndPoints.UPDATE_USERS_BY_ID);
        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test(priority = 4)
    public void deleteUserSuccessfully() {
        given().log().all()
                .pathParam("userId",userId)
                .when()
                .delete(EndPoints.DELETE_USERS_BY_ID)
                .then().log().all()
                .statusCode(200);

        given().log().all()
                .pathParam("userId",userId)
                .when()
                .get(EndPoints.UPDATE_USERS_BY_ID)
                .then().log().all().statusCode(404);
    }
}
