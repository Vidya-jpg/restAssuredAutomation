package test;

import io.restassured.response.Response;
import models.Books;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.booksService;

import java.util.List;

public class GetBookTests {
    private static final Books b=new Books();
    @BeforeClass(alwaysRun = true)
    public void createBookingData()
    {
        b.setId(12);
        b.setAuthor("jack");
        b.setName("doet");
        b.setPublished_year(2021);
        b.setBook_summary("scifi");
    }
    @Test(description = "Validate getting book by Id",priority = 1,groups = {"smoke"})
    public void getBookByidAuth()
    {
        booksService bs=new booksService();
        Response response=bs.getBookById(b.getId());
        System.out.print(response.jsonPath().get().toString());
        Assert.assertEquals(response.jsonPath().get("name"),b.getName());
        Assert.assertEquals(response.jsonPath().getInt("published_year"),b.getPublished_year());
        Assert.assertEquals(response.jsonPath().get("book_summary"),b.getBook_summary());
        Assert.assertEquals(response.jsonPath().getInt("id"),b.getId());
        Assert.assertEquals(response.jsonPath().get("author"),b.getAuthor());

    }
    @Test(description = "Validate getting book by Id Without Auth",priority = 2)
    public void getBookByidWithoutAuth()
    {
        booksService bs=new booksService();
        Response response=bs.getBookByIdWithoutAuth(b.getId());
        System.out.print(response.jsonPath().get().toString());
        Assert.assertEquals(response.jsonPath().get("detail"),"Not authenticated");
    }
    @Test(description = "Validate getting all books",priority = 3)
    public void getAllBooks()
    {
        booksService bs=new booksService();
        Response response=bs.getAllBooks();
        System.out.print(response.jsonPath().get().toString());
        List<String> names=response.jsonPath().getList("name");
        List<String> ids=response.jsonPath().getList("id");
        Assert.assertTrue(names.contains(b.getName()));
        Assert.assertTrue(ids.contains(b.getId()));
    }

}
