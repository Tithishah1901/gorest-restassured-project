package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI ="https://gorest.co.in";
        // RestAssured.port = 3030;
        response= given()
                .queryParam("page", 1)
                .queryParam("per_page",25)
                .when()
                .get("/public/v2/posts")
                .then().statusCode(200);
    }

//    Extraction Example
//1. Extract the title
@Test
public void extractTitle() {
    List<String> title = response.extract().path("title");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Extract the title : "+ title);
    System.out.println("------------------End of Test---------------------------");

}
//2. Extract the total number of record
@Test
public void extractTotal() {
List<String> records = response.extract().path("data");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Extract the total number of record : "+ records.size());
    System.out.println("------------------End of Test---------------------------");

}
//3. Extract the body of 15th record
@Test
public void extractTheBody() {
    String bodyRecords = response.extract().path("body[14]");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Extract the body of 15th record : "+bodyRecords );
    System.out.println("------------------End of Test---------------------------");

}
//4. Extract the user_id of all the records
@Test
public void extractTheAllUserIdRecord() {
    List<Integer> userIds = response.extract().path("user_id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Extract the user_id of all the records : "+ userIds);
    System.out.println("------------------End of Test---------------------------");

}
      //5. Extract the title of all the records
      @Test
      public void extractTheTitleRecord() {
          List<String> titleRecord = response.extract().path("title");

          System.out.println("------------------StartingTest---------------------------");
          System.out.println("Extract the title of all the records : "+ titleRecord);
          System.out.println("------------------End of Test---------------------------");

      }
//6. Extract the title of all records whose user_id = 5914200
@Test
public void extractTheTitleRecordUserId() {
    List<String> titleOfRecordUserId = response.extract().path("findAll{it.user_id == 7609179}.title");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Extract the title of all records whose user_id = 7609179: "+ titleOfRecordUserId);
    System.out.println("------------------End of Test---------------------------");

}
//7. Extract the body of all records whose id = 93957
@Test
public void extractTheBodyById() {
    List<String> bodyByIds  = response.extract().path("findAll{it.id == 184660}.body");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Extract the body of all records whose id == 184660: "+ bodyByIds);
    System.out.println("------------------End of Test---------------------------");

}
}
