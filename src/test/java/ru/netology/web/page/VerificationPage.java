package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id=code] input");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private final SelenideElement errorInvalidCode = $("[data-test-id=error-notification]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public void validVerify(String code) {
        codeField.setValue(code);
        verifyButton.click();
        new DashboardPage();
    }

    public void invalidVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        errorInvalidCode.shouldBe(visible);
    }

    public void getErrorIfInvalidSmsCode() {
        errorInvalidCode.shouldBe(visible).shouldHave(text("Ошибка! Неверно указан код! Попробуйте ещё раз"));
    }
}
