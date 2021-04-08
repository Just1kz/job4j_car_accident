package ru.job4j.car.accident;

import org.junit.Test;
import static org.junit.Assert.*;

public class SomeLogicTest {

    @Test
    public void test1() {
        assertTrue(SomeLogic.test());
    }

    @Test
    public void test2() {
        assertFalse(SomeLogic.test2());
    }
}