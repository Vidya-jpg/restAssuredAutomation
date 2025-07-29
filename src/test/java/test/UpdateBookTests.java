package test;

import io.restassured.response.Response;
import models.Books;
import org.testng.Assert;
import service.*;
import org.testng.annotations.*;

import static org.hamcrest.Matchers.notNullValue;

public class UpdateBookTests {
    private static final Books b=new Books();
    String token;
    @BeforeClass
    public void createBookingData()
    {
        b.setId(12);
        b.setAuthor("jack");
        b.setName("doet");
        b.setPublished_year(2021);
        b.setBook_summary("scifi");
    }
    @Test(description = "Validate updating book",priority = 1)
    public void updateBook()
    {
        booksService bs=new booksService();
        b.setName("test");
        System.out.print(b.getId()+b.getName());
        Response response=bs.updateBook(b.getId(),b);
        System.out.print(response.jsonPath().get().toString());
        Assert.assertEquals(response.jsonPath().get("name"),b.getName());
        Assert.assertEquals(response.jsonPath().getInt("published_year"),b.getPublished_year());
        Assert.assertEquals(response.jsonPath().get("book_summary"),b.getBook_summary());
        Assert.assertEquals(response.jsonPath().getInt("id"),b.getId());
        Assert.assertEquals(response.jsonPath().get("author"),b.getAuthor());

    }
    @Test(description = "Validate updating book without authentication",priority = 2)
    public void updateBookWithoutAuth()
    {
        booksService bs=new booksService();
        b.setName("test");
        System.out.print(b.getId()+b.getName());
        Response response=bs.updateBookWithoutAuth(b.getId(),b);
        System.out.print(response.jsonPath().get().toString());
        Assert.assertEquals(response.jsonPath().get("detail"),"Not authenticated");

    }
    @Test(description = "Validate updating book without bookid",priority = 2)
    public void updateBookWithoutBookid()
    {
        booksService bs=new booksService();
        b.setName("test");
        System.out.print(b.getId()+b.getName());
        Response response=bs.updateBookWithoutID(b);
        System.out.print(response.jsonPath().get().toString());
        Assert.assertEquals(response.jsonPath().get("detail"),"Method Not Allowed");
    }

}
