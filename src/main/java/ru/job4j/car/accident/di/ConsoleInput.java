package ru.job4j.car.accident.di;

import org.springframework.stereotype.Component;

@Component
public class ConsoleInput {
    public void print(String string) {
        System.out.println(string);
    }
}
