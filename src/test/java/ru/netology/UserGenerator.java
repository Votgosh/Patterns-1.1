package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UserGenerator {

    public static DataUser generateUser(int changeDay) {
        String city = generateCity();
        String name = generateName();
        String phone = generatePhone();
        return new DataUser(city, name, phone);
    }

    public static String generateCity() {
        String[] cities = new String[]{
                "Грозный", "Чебоксары", "Барнаул", "Чита", "Петропавловск-Камчатский",
                "Краснодар", "Красноярск", "Пермь", "Владивосток", "Ставрополь", "Хабаровск",
                "Благовещенск", "Архангельск", "Астрахань", "Белгород", "Брянск", "Владимир",
                "Волгоград", "Вологда", "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга",
        };
        Faker faker = new Faker();
        int index = faker.number().numberBetween(0, cities.length -1);
        return cities[index];
    }

    public static String generateDate(int changeDays){
        return LocalDate.now().plusDays(changeDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateName(){
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().lastName() + " " + faker.name().firstName();
    }
    public static String generatePhone(){
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }
}
