package test;

import io.restassured.response.Response;
import models.Books;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.bookService;

public class createBookTests {
    private static final Books b=new Books();
    String token;
    @BeforeClass(alwaysRun = true)
    public void createBookingData()
    {
        b.setId(12);
        b.setAuthor("jack");
        b.setName("doet");
        b.setPublished_year(2021);
        b.setBook_summary("scifi");
    }
    @Test(description = "Validate creating a new book with Auth",priority = 1,groups = {"smoke"})
    public void CreateBookAuthTest()
    {
        bookService bs=new bookService();
        Response response=bs.createBookWithAuth(b);
        System.out.print(response.toString());
        Assert.assertEquals(response.jsonPath().get("name"),b.getName());
        Assert.assertEquals(response.jsonPath().getInt("published_year"),b.getPublished_year());
        Assert.assertEquals(response.jsonPath().get("book_summary"),b.getBook_summary());
        Assert.assertEquals(response.jsonPath().getInt("id"),b.getId());
        Assert.assertEquals(response.jsonPath().get("author"),b.getAuthor());
        System.out.print(response.jsonPath().get().toString());
    }
    @Test(description = "Validate creating a new book without Auth",priority = 2)
    public void CreateBookUnauthTest()
    {
        bookService bs=new bookService();
        Response response=bs.createBookWithoutAuth(b);
        System.out.print(response.toString());
        System.out.print(response.jsonPath().get().toString());
        Assert.assertEquals(response.jsonPath().get("detail"),"Not authenticated");
    }
    @Test(description = "Validate creating a new book without Auth",priority = 2)
    public void CreateBookWithoutPayloadTest()
    {
        bookService bs=new bookService();
        Response response=bs.createBookWithoutPayload();
        System.out.print(response.toString());
        System.out.print(response.jsonPath().get().toString());
        System.out.print(response.jsonPath().get("detail[0].type").toString());
        Assert.assertEquals(response.jsonPath().get("detail[0].type"),"missing");
        Assert.assertEquals(response.jsonPath().get("detail[0].msg"),"Field required");
    }
}
