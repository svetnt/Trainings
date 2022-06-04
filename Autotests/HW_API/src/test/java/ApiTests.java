import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTests {
    @Test
    void getListUsersTest(){
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(12))
                .body("data.first_name", hasItems("Michael", "Tobias", "Rachel"));
    }

    @Test
    void postCreateTest(){
        String body = "{ \"name\": \"michel\", \"job\": \"leader\" }";

        given()
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
    }

    @Test
    void deleteUserTest(){
        given()
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .statusCode(204);
    }

    @Test
    void postUnsuccessfulLoginTest(){
        String body = "{\"email\": \"test@test.com\"}";

        given()
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
    }

    @Test
    void getNotFoundTest(){
        Response response = given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404)
                .extract().response();

        assertEquals("{}", response.asString());
    }
}
