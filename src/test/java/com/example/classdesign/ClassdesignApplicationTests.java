package com.example.classdesign;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

@SpringBootTest
class ClassdesignApplicationTests {

    @Test
    void contextLoads() throws IOException {
        String mkey = RandomStringUtils.randomAlphanumeric(8);
        System.out.println(mkey);
    }

}
