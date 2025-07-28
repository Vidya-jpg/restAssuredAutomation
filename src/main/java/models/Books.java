package models;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Books {
    @JsonProperty("id")
    int id;
    @JsonProperty("name")
    String name;
    @JsonProperty("author")
    String author;
    @JsonProperty("published_year")
    int published_year;
    @JsonProperty("book_summary")
    String book_summary;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPublished_year() {
        return published_year;
    }

    public String getAuthor() {
        return author;
    }

    public String getBook_summary() {
        return book_summary;
    }

    public String getName() {
        return name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBook_summary(String book_summary) {
        this.book_summary = book_summary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublished_year(int published_year) {
        this.published_year = published_year;
    }
}
