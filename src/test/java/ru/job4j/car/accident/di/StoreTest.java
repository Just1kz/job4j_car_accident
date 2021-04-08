package ru.job4j.car.accident.di;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreTest {

    @Test
    public void getAll() {
        Store store = new Store();
        store.add("123");
        assertThat(store.getAll().toString(), is("[123]"));
    }
}