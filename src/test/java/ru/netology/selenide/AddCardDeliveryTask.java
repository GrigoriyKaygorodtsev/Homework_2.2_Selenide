package ru.netology.selenide;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class AddCardDeliveryTask {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


        @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldBeSuccessfullyCompleted() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Южно-Сахалинск");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Макаров Иван");
        $("[data-test-id='phone'] input").setValue("+79000000666");
        $("[data-test-id='agreement']").click();
        $$("button.button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification'] .notification__title")
                .shouldHave(exactText("Успешно!"), Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content")
               .shouldHave(exactText("Встреча успешно забронирована на " + planningDate));

    }
}
