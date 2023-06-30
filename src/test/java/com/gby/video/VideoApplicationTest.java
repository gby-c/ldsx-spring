package com.gby.video;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

@SpringBootTest
public class VideoApplicationTest {
    @Test
    public void contextLoads() {
        Instant instant = Instant.now();
        System.out.println(instant.toEpochMilli());

    }
}
