package utils;

public class AccessTokenManager {

    private static String accessToken;
    public static void setAccessToken(String token) {
        accessToken = token;
        System.out.println("Access Token set: " + (token != null ? "******" + token.substring(token.length() - 5) : "null")); // Mask token for logs
    }

    /**
     * Retrieves the current access token.
     * @return The JWT token string, or null if not set.
     */
    public static String getAccessToken() {
        if (accessToken == null) {
            System.err.println("Warning: Access Token is null. Ensure login was successful.");
        }
        return accessToken;
    }

    /**
     * Clears the access token. Useful for logout or cleanup scenarios.
     */
    public static void clearAccessToken() {
        accessToken = null;
        System.out.println("Access Token cleared.");
    }


}
