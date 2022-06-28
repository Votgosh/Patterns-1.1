package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor

public class DataUser {
    private final String city;
    private final String name;
    private final String phone;
}