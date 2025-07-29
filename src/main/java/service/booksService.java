package service;


import constants.commonConstant;
import io.restassured.response.Response;
import models.Books;
import utils.RequestBuilder;
import utils.commonUtils;

    public class booksService {

        private final commonUtils utils = new commonUtils();
        private static final String BASE_URL = commonConstant.base_URI;
        private static final String BOOKS_ENDPOINT = commonConstant.booksEndPoint;

        private String buildEndpoint(String path) {
            return BASE_URL + BOOKS_ENDPOINT + path;
        }

        private Response sendRequestWithAuth(String endpoint, String method, Object payload, int expectedStatus) {
            return RequestBuilder.getRequest(BASE_URL + BOOKS_ENDPOINT, true)
                    .body(payload != null ? utils.convertToJsonFormat((Books) payload) : "")
                    .request(method, endpoint)
                    .then()
                    .statusCode(expectedStatus)
                    .log().body()
                    .extract().response();
        }

        private Response sendRequestWithoutAuth(String endpoint, String method, Object payload, int expectedStatus) {
            return RequestBuilder.getRequest(BASE_URL + BOOKS_ENDPOINT, false)
                    .body(payload != null ? utils.convertToJsonFormat((Books) payload) : "")
                    .request(method, endpoint)
                    .then()
                    .statusCode(expectedStatus)
                    .log().body()
                    .extract().response();
        }

        public Response createBookWithAuth(Books book) {
            System.out.println("Attempting to create book: " + book.getName());
            return sendRequestWithAuth(buildEndpoint("/"), "POST", book, 200);
        }

        public Response createBookWithoutAuth(Books book) {
            System.out.println("Attempting to create book without auth: " + book.getName());
            return sendRequestWithoutAuth(buildEndpoint("/"), "POST", book, 403);
        }

        public Response createBookWithoutPayload() {
            System.out.println("Attempting to create book without payload");
            return sendRequestWithAuth(buildEndpoint("/"), "POST", null, 422);
        }

        public Response getBookById(int bookId) {
            System.out.println("Attempting to get book with ID: " + bookId);
            return sendRequestWithAuth(buildEndpoint("/" + bookId), "GET", null, 200);
        }

        public Response getBookByIdWithoutAuth(int bookId) {
            System.out.println("Attempting to get book with ID: " + bookId + " without auth");
            return sendRequestWithoutAuth(buildEndpoint("/" + bookId), "GET", null, 403);
        }

        public Response getAllBooks() {
            System.out.println("Attempting to get all books.");
            return sendRequestWithAuth(buildEndpoint(""), "GET", null, 200);
        }

        public Response updateBook(int bookId, Books updatedBook) {
            System.out.println("Attempting to update book with ID: " + bookId);
            return sendRequestWithAuth(buildEndpoint("/" + bookId), "PUT", updatedBook, 200);
        }

        public Response updateBookWithoutAuth(int bookId, Books updatedBook) {
            System.out.println("Attempting to update book with ID: " + bookId + " without auth");
            return sendRequestWithoutAuth(buildEndpoint("/" + bookId), "PUT", updatedBook, 403);
        }

        public Response updateBookWithoutID(Books updatedBook) {
            System.out.println("Attempting to update book without giving ID");
            return sendRequestWithAuth(buildEndpoint("/"), "PUT", updatedBook, 405);
        }

        public Response deleteBook(int bookId) {
            System.out.println("Attempting to delete book with ID: " + bookId);
            return sendRequestWithAuth(buildEndpoint("/" + bookId), "DELETE", null, 200);
        }

        public Response deleteBookWithoutAuth(int bookId) {
            System.out.println("Attempting to delete book with ID: " + bookId + " without auth");
            return sendRequestWithoutAuth(buildEndpoint("/" + bookId), "DELETE", null, 403);
        }
    }

