package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidLoginAuthInfo() {
        Faker faker = new Faker();
        return new AuthInfo(faker.name().firstName(), "qwerty123");
    }

    public static AuthInfo getInvalidPasswordAuthInfo() {
        Faker faker = new Faker();
        return new AuthInfo("vasya", faker.internet().password());
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getWrongVerificationCode() {
        String code = "12345";
        return new VerificationCode(code);
    }
}
