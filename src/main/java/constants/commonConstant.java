package constants;
import utils.fileReader;

import java.io.IOException;

public class commonConstant {

    //api end points
    public static String base_URI;
    public static final String booksEndPoint;
    public static final String signupEndPoint;
    public static final String loginEndPoint;
    public static final String healthserviceEndPoint;
    public static String tokenfilepath;
    static {
        try {
            base_URI = fileReader.getEnvbasedURL(fileReader.getPropertyFromFile("ENV"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        booksEndPoint = "/books";
        signupEndPoint = "/signup";
        loginEndPoint = "/login";
        healthserviceEndPoint = "/health";
        tokenfilepath="output.json";
    }
}
