package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Books;

public class commonUtils {

    public String convertToJsonFormat(Books book)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        String authRequestJson;
        try {
            authRequestJson = objectMapper.writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.print(authRequestJson);
        return authRequestJson;
    }
}
