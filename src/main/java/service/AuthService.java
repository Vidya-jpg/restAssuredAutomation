package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.commons.io.FileUtils;
import utils.AccessTokenManager;
import constants.commonConstant;
import io.restassured.response.Response;
import models.userAuth;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.MatcherAssert.assertThat;

public class AuthService {

        /**
         * Sends a POST request to the /signup endpoint to register a new user.
         * @param user The User object containing username and password.
         * @return The Rest Assured Response object.
         */
        public String signupUser(userAuth user) {
            Response response= given()
                    .contentType("application/json") // Specify content type as JSON
                    .body(user) // Set the request body with the User object
                    .when()
                    .post(commonConstant.base_URI+ commonConstant.signupEndPoint) // Send POST request to signup endpoint
                    .then()
                    .log().all()
                    .statusCode(200)
                    .extract()
                    .response();
            String msg= response.body().jsonPath().get("message");
            return msg;
        }

        /**
         * Sends a POST request to the /login endpoint to authenticate a user.
         * @param user The User object containing username and password.
         * @return The Rest Assured Response object.
         */
        public void loginUser(userAuth user) {
            System.out.println("Attempting to log in user: " + user.getEmail());
            ObjectMapper objectMapper = new ObjectMapper();
            String authRequestJson;
            try {
                authRequestJson = objectMapper.writeValueAsString(user);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            Response loginresponse=  given()
                    .contentType("application/json") // Specify content type as JSON
                    .accept("application/json")
                    .body(authRequestJson) // Set the request body with the User object
                    .when()
                    .post(commonConstant.base_URI+ commonConstant.loginEndPoint) // Send POST request to login endpoint
                    .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();
            // Extract and return the response
            AccessTokenManager.setAccessToken(loginresponse.jsonPath().get("access_token"));
            String token=AccessTokenManager.getAccessToken();
            System.out.print(token);
            File outputFile = new File("output.json");
            try {
                FileUtils.writeStringToFile(outputFile, token, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("JSON response written to output.json");
            //return response;
        }

        /**
         * Helper method to parse the login response and extract the AuthResponse object.
         * @param response The Rest Assured Response object from a login call.
         * @return An AuthResponse object if parsing is successful, null otherwise.
         */
        public userAuth getAuthResponse(Response response) {
            try {
                return response.as(userAuth.class);
            } catch (Exception e) {
                System.err.println("Failed to parse AuthResponse: " + e.getMessage());
                return null;
            }
        }
    }

