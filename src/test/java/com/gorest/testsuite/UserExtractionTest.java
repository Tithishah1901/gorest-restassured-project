package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase {

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
                .then().log().all().statusCode(200);

    }

//    Write the following test inside UserExtractionTest class.
//    Extraction Example
//1. Extract the All Ids
@Test
public void extractAllId() {
    List<Integer> allId = response.extract().path("id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all th Id : "+ allId);
    System.out.println("------------------End of Test---------------------------");

}
//2. Extract the all Names
@Test
public void extractAllName() {
    List<String> allName = response.extract().path("name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all the Name : "+ allName );
    System.out.println("------------------End of Test---------------------------");

}
//3. Extract the name of 5th object
@Test
public void extractTheNameOfTheObject() {
    String objectName = response.extract().path("name[4]");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get the Name from object : "+ objectName );
    System.out.println("------------------End of Test---------------------------");

}
//4. Extract the names of all object whose status = inactive
@Test
public void extractTheStatus() {
    List<?> status = response.extract().path("findAll{it.status == 'inactive'}.name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get the status : "+ status );
    System.out.println("------------------End of Test---------------------------");

}
//5. Extract the gender of all the object whose status = active
@Test
public void extractTheGender() {
    List<String> activeStatus = response.extract().path("findAll{it.status == 'active'}.gender");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get the status : "+ activeStatus );
    System.out.println("------------------End of Test---------------------------");

}
//6. Print the names of the object whose gender = female
@Test
public void printTheNameGender() {
    List<String> femaleNames = response.extract().path("findAll{it.gender == 'female'}.name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get the status : "+ femaleNames );
    System.out.println("------------------End of Test---------------------------");

}
//7. Get all the emails of the object where status = inactive
@Test
public void getAllTheEmailOfInactive() {
    List<String> email = response.extract().path("findAll{it.status == 'inactive'}.email");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all the emails of the object where status = inactive  : "+ email );
    System.out.println("------------------End of Test---------------------------");

}

//8. Get the ids of the object where gender = male
@Test
public void getAllTheIDs() {
    List<String> getAllTheIds = response.extract().path("findAll{it.gender == 'male'}.ids");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all the emails of the object where status = inactive  : "+ getAllTheIds );
    System.out.println("------------------End of Test---------------------------");

}
//9. Get all the status
@Test
public void getAllTheStatus() {
    List<String> allStatus = response.extract().path("status");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all the status  : "+ allStatus );
    System.out.println("------------------End of Test---------------------------");

}
//10. Get email of the object where name = Lal Dwivedi
@Test
public void getEmailOfTheUser() {
    String emailOfTheUser = response.extract().path("findAll{it.name == 'Manisha Talwar'}.email");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get email of the user  : "+ emailOfTheUser );
    System.out.println("------------------End of Test---------------------------");

}
//11. Get gender of id = 5914189
@Test
public void getGenderOfId() {
    List<Integer> iDs = response.extract().path("findAll{it.gender == 'female'}.id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get email of the user  : "+ iDs);
    System.out.println("------------------End of Test---------------------------");

}
}
