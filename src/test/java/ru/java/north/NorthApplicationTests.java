package ru.java.north;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NorthApplicationTests {
    @Test
    void contextLoads() {
        Assertions.assertEquals(1 + 1, 2);
    }
}
