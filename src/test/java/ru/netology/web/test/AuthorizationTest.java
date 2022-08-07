package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.sql.DbInteraction;

import static com.codeborne.selenide.Selenide.open;

public class AuthorizationTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    public static void cleanDb() {
        DbInteraction.cleanDataBase();
    }

    @Test
    public void shouldAuthorization() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validData(authInfo);
        verificationPage.validVerify(DbInteraction.getCode());
    }

    @Test
    public void shouldGetErrorIfInvalidPassword() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getInvalidPasswordAuthInfo();
        loginPage.invalidData(authInfo);
        loginPage.getErrorIfInvalidData();
    }

    @Test
    public void shouldGetErrorIfInvalidLogin() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getInvalidLoginAuthInfo();
        loginPage.invalidData(authInfo);
        loginPage.getErrorIfInvalidData();
    }

    @Test
    public void shouldGetErrorIfWrongCode() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validData(authInfo);
        val code = DataHelper.getWrongVerificationCode();
        verificationPage.invalidVerify(code);
        verificationPage.getErrorIfInvalidSmsCode();
    }

    @Test
    public void shouldGetErrorIfPasswordIncorrectThreeTimes() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getInvalidPasswordAuthInfo();
        loginPage.invalidData(authInfo);
        loginPage.getErrorIfInvalidData();
        loginPage.cleaning();
        loginPage.invalidData(authInfo);
        loginPage.getErrorIfInvalidData();
        loginPage.cleaning();
        loginPage.invalidData(authInfo);
        loginPage.getBlockedMessage();
    }
}
