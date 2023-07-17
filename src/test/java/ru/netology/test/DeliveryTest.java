package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.SHIFT;


class DeliveryTest {
    String city = DataGenerator.generateCity();
    String name = DataGenerator.generateName();
    String phoneNumber = DataGenerator.generatePhoneNumber();


    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }


    @Test
    @DisplayName("Should plan meeting with success")
    void shouldPlanMeetingForCard() {

        DataGenerator.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
        int dayToAddForFirstMeeting = 4;
        String firstMeetingDate = DataGenerator.generateDate(dayToAddForFirstMeeting);
        int dayToAddForSecondMeeting = 7;
        String secondDate = DataGenerator.generateDate(dayToAddForSecondMeeting);

        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=date] input").sendKeys(Keys.chord(SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phoneNumber);
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate)).shouldBe(Condition.visible);

        $("[data-test-id=date] input").sendKeys(Keys.chord(SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(firstMeetingDate);
        $(byText("Запланировать")).click();
        $("[data-test-id='replan-notification'] notification__content")
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(Condition.visible);


        $("[data-test-id='replan-notification] button").click();
        $("[data-test-id='success-notification] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + secondDate)).shouldBe(Condition.visible);
    }
}