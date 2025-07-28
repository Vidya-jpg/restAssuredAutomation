package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import service.AuthService;

public class RequestBuilder {

    public static RequestSpecification withAuth(String URI) {
        return RestAssured.given()
                .baseUri(URI)
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Authorization", "Bearer " + fileReader.readFromFile("output.json"));
    }

    public static RequestSpecification withoutAuth(String URI) {
        return RestAssured.given()
                .baseUri(URI)
                .contentType(ContentType.JSON);
    }

    public static RequestSpecification getRequest(String URI,boolean withAuth) {
        return withAuth ? withAuth(URI) : withoutAuth(URI);
    }
}
