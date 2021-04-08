package ru.job4j.car.accident.di;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ConsoleInput {
    public void print(String string) {
        System.out.println(string);
    }
}
