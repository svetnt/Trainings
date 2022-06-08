package tests;

import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("API tests")
public class ApiTests {
    @Test
    @DisplayName("Get list users")
    @Feature("Get")
    void getListUsersTest(){
        step("GET /api/users?page=2", () -> {
            given()
                    .filter(withCustomTemplates())
                    .log().uri()
                    .when()
                    .get("https://reqres.in/api/users?page=2")
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .body("total", is(12))
                    .body("data.first_name", hasItems("Michael", "Tobias", "Rachel"));
        });
    }

    @Test
    @DisplayName("Create user")
    @Feature("Post")
    void postCreateTest(){
        String body = "{ \"name\": \"michel\", \"job\": \"leader\" }";

        step("POST /api/users", () -> {
        given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .when()
                .body(body)
                .contentType(JSON)
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("michel"))
                .body("job", is("leader"));
        });
    }

    @Test
    @DisplayName("Delete user")
    @Feature("Delete")
    void deleteUserTest(){
        step("DELETE /api/users/2", () -> {
        given()
                .filter(withCustomTemplates())
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .statusCode(204);
        });
    }

    @Test
    @DisplayName("Unsuccessful login")
    @Feature("Post")
    void postUnsuccessfulLoginTest(){
        String body = "{\"email\": \"test@test.com\"}";

        step("POST /api/login", () -> {
        given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .when()
                .body(body)
                .contentType(JSON)
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
        });
    }

    @Test
    @DisplayName("Get nonexistent user")
    @Feature("Get")
    void getNotFoundTest(){
        step("GET /api/users/23", () -> {
        Response response = given()
                .filter(withCustomTemplates())
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404)
                .extract().response();

        assertEquals("{}", response.asString());
        });
    }
}
