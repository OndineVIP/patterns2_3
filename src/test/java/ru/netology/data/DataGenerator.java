package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {
    private DataGenerator() {
    }

     static Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int addDays) {
        var date = LocalDate.now().plusDays(addDays);
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateName() {
        return faker.name().fullName();
    }

    public static String generatePhoneNumber() {
        return faker.numerify("+###########");
    }

    public static String generateCity() {
        var cities = new String[]{
                "Москва", "Смоленск", "Хабаровск", "Псков", "Омск", "Нижний Новгород"
        };

        return cities[new Random().nextInt(cities.length)];

    }


    public static class Registration {
        private Registration() {

        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(), generatePhoneNumber());
        }
    }


    @Value
    public static class UserInfo {
        String login;
        String password;
        String status;
    }
}
