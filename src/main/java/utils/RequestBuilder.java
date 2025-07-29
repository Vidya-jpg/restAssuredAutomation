package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import service.AuthService;

public class RequestBuilder {
    private static final String APPLICATION_JSON = "application/json";
    private static final String AUTH_HEADER = "Authorization";
    public static RequestSpecification withAuth(String URI) {
        return RestAssured.given()
                .baseUri(URI)
                .contentType(ContentType.JSON)
                .accept(APPLICATION_JSON)
                .header(AUTH_HEADER, "Bearer " + fileReader.readFromFile("output.json"));
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
