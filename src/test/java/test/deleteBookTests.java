package test;

import io.restassured.response.Response;
import models.Books;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.bookService;

public class deleteBookTests {
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

    @Test(description = "Validate Deleting book",priority = 1)
    public void deleteBook()
    {
        bookService bs=new bookService();
        Response r=bs.deleteBook(b.getId());
        Assert.assertEquals(r.jsonPath().get("message"),"Book deleted successfully");
    }

    @Test(description = "Validate Deleting book Without Auth",priority = 2)
    public void deleteBookWithoutAuth()
    {
        bookService bs=new bookService();
        Response r=bs.deleteBookWithoutAuth(b.getId());
        Assert.assertEquals(r.jsonPath().get("detail"),"Not authenticated");
    }
}
