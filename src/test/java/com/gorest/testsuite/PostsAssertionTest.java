package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest extends TestBase {

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
//1. Verify the if the total record is 25
@Test
public void verifyTheTotal(){
    response.body("total",hasSize(25));
}
//2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti cohaero libero.”
@Test
public void verifyTheTitle(){
   response.body("[0].title",equalTo("Quia thymum totam aspernatur sperno."));
}
       //3. Check the single user_id in the Array list (5914249)
       @Test
       public  void checkSingleUserId(){
        response.body("user_id",hasItem(7609178));
}
//4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
@Test
public void checkMultipleIds() {
    response.body("id", hasItems(184662,184660,184661));
}
//5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus.Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo.
//Villa ususvos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptiotumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
//acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
@Test
public void veriyTheBodyOfUser() {
    response.body("[5].body", equalTo("Confido uberrime copia. Vestrum sui solium. Debilito ambitus volup. Solus id adhaero. Strenuus trepide vespillo. Concido thermae canis. Ars vesper patria. Trucido rerum adulatio. Tutis denego animi. Adversus sumo viduo. Voluntarius et vero. Confido vel defendo. Sed fugiat tamquam. Suggero callide depulso. Traho eaque conicio. Antea armo dens. Aedificium argentum quisquam. Apud cupiditate tredecim. Pecunia inventore nam."));
}
}
