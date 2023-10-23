package utils;

import java.util.List;
import java.util.Random;

public class Generator {
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static Random random = new Random();

    public static String generateLogin(Integer length){
        StringBuilder login = new StringBuilder();
        for (int i = 0; i < length; i++) {
            login.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return login.toString();
    }
    public static String generatePassword(Integer length) {
        StringBuilder password = new StringBuilder();
        password.append(random.nextInt(10));
        for (int i = 0; i < length-1; i++) {
            password.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return password.toString();
    }
    public static String generateEmail(){
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < random.nextInt(15); i++) {
            email.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        email.append("@gmail.com");
        return email.toString();
    }
}
