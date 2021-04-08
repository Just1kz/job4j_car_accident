package ru.job4j.car.accident.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StartUI {
    @Autowired
    private Store store;

    @Autowired
    private ConsoleInput consoleInput;

//    public StartUI(Store store, ConsoleInput consoleInput) {
//        this.store = store;
//        this.consoleInput = consoleInput;
//    }

    public void add(String value) {
        store.add(value);
    }

    public void print() {
        for (String value : store.getAll()) {
            consoleInput.print(value);
        }
    }
}
