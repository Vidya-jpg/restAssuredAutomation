package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.commonConstant;
import io.restassured.response.Response;
import models.Books;
import org.testng.annotations.BeforeClass;
import utils.RequestBuilder;
import utils.fileReader;
import utils.commonUtils;

import static io.restassured.RestAssured.given;

public class bookService {
    private static final Books b=new Books();
    commonUtils c=new commonUtils();
    public Response createBookWithAuth(Books book) {
        System.out.println("Attempting to create book: " + book.getName());
        return RequestBuilder.getRequest(commonConstant.base_URI+ commonConstant.booksEndPoint+"/",true)
                .body(c.convertToJsonFormat(book))
                .redirects().follow(true)
                .post(commonConstant.base_URI+ commonConstant.booksEndPoint+"/")
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
    }
    public Response createBookWithoutAuth(Books book) {
        System.out.println("Attempting to create book without auth: " + book.getName());
        return RequestBuilder.getRequest(commonConstant.base_URI+ commonConstant.booksEndPoint+"/",false)
                .body(c.convertToJsonFormat(book))
                .post(commonConstant.base_URI+ commonConstant.booksEndPoint+"/")
                .then()
                .statusCode(403)
                .log().body()
                .extract().response();
    }

    public Response createBookWithoutPayload() {
        System.out.println("Attempting to create book without payload: ");
        return RequestBuilder.getRequest(commonConstant.base_URI+ commonConstant.booksEndPoint+"/",true)
                .post(commonConstant.base_URI+ commonConstant.booksEndPoint+"/")
                .then()
                .statusCode(422)
                .log().body()
                .extract().response();
    }
    public Response getBookById(int bookId) {
        System.out.println("Attempting to get book with ID: " + bookId);
        return  RequestBuilder.getRequest(commonConstant.base_URI+ commonConstant.booksEndPoint+"/",true)                .when()
                .get(commonConstant.base_URI+ commonConstant.booksEndPoint + "/"+bookId) // GET request to specific book ID
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
    }
    public Response getBookByIdWithoutAuth(int bookId) {
        System.out.println("Attempting to get book with ID: " + bookId);
        return RequestBuilder.getRequest(commonConstant.base_URI+ commonConstant.booksEndPoint+"/",false)
                .get(commonConstant.base_URI+ commonConstant.booksEndPoint + "/"+bookId) // GET request to specific book ID
                .then()
                .statusCode(403)
                .log().body()
                .extract().response();
    }
    public Response getAllBooks() {
        System.out.println("Attempting to get all books.");
        return RequestBuilder.getRequest(commonConstant.base_URI+ commonConstant.booksEndPoint+"/",true)
                .get(commonConstant.base_URI+ commonConstant.booksEndPoint) // GET request to all books endpoint
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
    }
    public Response updateBook(int bookId, Books updatedBook) {
        System.out.println("Attempting to update book with ID: " + bookId);
        return RequestBuilder.getRequest(commonConstant.base_URI+ commonConstant.booksEndPoint+"/"+bookId,true)
                .body(c.convertToJsonFormat(updatedBook))
                .put(commonConstant.base_URI+ commonConstant.booksEndPoint + "/"+bookId) // PUT request to specific book ID
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
    }
    public Response updateBookWithoutAuth(int bookId, Books updatedBook) {
        System.out.println("Attempting to update book with ID: " + bookId);
        return RequestBuilder.getRequest(commonConstant.base_URI+ commonConstant.booksEndPoint+"/"+bookId,false)
                .body(c.convertToJsonFormat(updatedBook))
                .when()
                .put(commonConstant.base_URI+ commonConstant.booksEndPoint + "/"+bookId) // PUT request to specific book ID
                .then()
                .statusCode(403)
                .log().body()
                .extract().response();
    }
    public Response updateBookWithoutID(Books updatedBook) {
        System.out.println("Attempting to update book without giving ID: ");
        return RequestBuilder.getRequest(commonConstant.base_URI+ commonConstant.booksEndPoint+"/",true)
                .body(c.convertToJsonFormat(updatedBook))
                .when()
                .put(commonConstant.base_URI+ commonConstant.booksEndPoint + "/") // PUT request to specific book ID
                .then()
                .statusCode(405)
                .log().body()
                .extract().response();
    }
    public Response deleteBook(int bookId) {
        System.out.println("Attempting to delete book with ID: " + bookId);
        return RequestBuilder.getRequest(commonConstant.base_URI+ commonConstant.booksEndPoint+"/"+bookId,true)
                .delete(commonConstant.base_URI+ commonConstant.booksEndPoint + "/"+bookId) // DELETE request to specific book ID
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
    }
    public Response deleteBookWithoutAuth(int bookId) {
        System.out.println("Attempting to delete book with ID: " + bookId);
        return given()
                .when()
                .delete(commonConstant.base_URI+ commonConstant.booksEndPoint + "/"+bookId) // DELETE request to specific book ID
                .then()
                .statusCode(403)
                .log().body()
                .extract().response();
    }
}
