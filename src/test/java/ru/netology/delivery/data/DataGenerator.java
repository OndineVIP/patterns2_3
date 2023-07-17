package ru.netology.delivery.data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataGenerator {
    private DataGenerator() {
    }
    static Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String generateCity() {
        var cities = new String[]{
                "Москва", "Смоленск", "Хабаровск", "Псков", "Омск", "Нижний Новгород"
        };
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName() {
                return faker.name().fullName();
    }

    public static String generatePhoneNumber() {
                return faker.numerify("+###########");
    }

      public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser() {
            String city = generateCity();
            String name = generateName();
            String phone = generatePhoneNumber();
            return new UserInfo(city, name, phone);
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}