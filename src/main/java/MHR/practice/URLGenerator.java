package MHR.practice;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class URLGenerator {
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String toBase62(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(BASE62.charAt((int) (num % 62)));
            num /= 62;
        }
        return sb.reverse().toString();}

    public static long hashURL(String url) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(url.getBytes());
            return (long) Math.abs(ByteBuffer.wrap(hashBytes).getLong()*(Math.random()+1));
        } catch (Exception e) {
            throw new RuntimeException("Error hashing URL", e);
        }
    }

    public String generateURL(String url) {
        return toBase62(hashURL(url));
    }
}
