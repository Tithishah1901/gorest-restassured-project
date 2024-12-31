package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {


    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI ="https://gorest.co.in";
       // RestAssured.port = 3030;
        response= given()
                .queryParam("page", 1)
                .queryParam("per_page",20)
                .when()
                .get("/public/v2/users")
                .then().statusCode(200);

    }

//Assert the following:
// 1. Verify the if the total record is 20
    @Test
    public void verifyTheTotal(){
        response.body("total",hasSize(20));
    }
//2. Verify the if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
    @Test
    public void verifyName(){
         response.body("[1].name",equalTo("Acharyanandana Dutta"));
    }
//3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
    @Test
    public  void checkSingleName(){
        response.body("name",hasItem("Shakuntala Ganaka"));
    }
//4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
@Test
public  void checkMultipleName(){
    response.body("name",hasItems("Shakuntala Ganaka","Acharyanandana Dutta","Kashyapi Patel"));
}
//5. Verify the emai of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
@Test
public  void verifyEmail(){
    response.body("[1].email",equalTo("dutta_acharyanandana@mitchell.example"));
}
//6. Verify the status is “Active” of user name is “Amaresh Rana”
@Test
public  void verifyActiveUserName(){
    response.body("[3].status",equalTo("active"));
}
//7. Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”
@Test
public  void verifyGender(){
    response.body("[2].gender",equalTo("male"));
}
}
