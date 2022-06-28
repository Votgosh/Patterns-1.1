package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    SelenideElement form = $x("//form");
    SelenideElement success = $x("//div[@data-test-id='success-notification']");
    SelenideElement replan = $x("//div[@data-test-id='replan-notification']");
    SelenideElement error = $x("//div[@data-test-id='error-notification']");


    @BeforeEach
    public void setup() {
        open("http://localhost:9999/");
    }

    @Test
    public void happyPath() {
        DataUser user = UserGenerator.generateUser(6);
        form.$x(".//input[@placeholder=\"Город\"]").val(user.getCity());
        form.$x(".//span[@data-test-id='date']//input[@class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$x(".//span[@data-test-id='date']//input[@class='input__control']").val(UserGenerator.generateDate(6));
        form.$x(".//input[@name=\"name\"]").val(user.getName());
        form.$x(".//input[@name=\"phone\"]").val(user.getPhone());
        form.$x(".//span[@class=\"checkbox__box\"]").click();
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();

        success.should(visible, Duration.ofSeconds(15));
        success.$x(".//div[@class='notification__content']").should(text("Встреча успешно запланирована на " +
                UserGenerator.generateDate(6)));
        success.$x(".//button").click();
        success.should(hidden);

        form.$x(".//input[@placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$x(".//input[@placeholder=\"Дата встречи\"]").val(UserGenerator.generateDate(5));
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();

        replan.should(visible, Duration.ofSeconds(15));
        replan.$x(".//span[contains(text(), 'Перепланировать')]//ancestor::button").click();
        replan.should(hidden);

        success.should(visible);
        success.$x(".//div[@class='notification__content']").should(text("Встреча успешно запланирована на " +
                UserGenerator.generateDate(5)));
        success.$x(".//button").click();
        success.should(hidden);
    }
}
