package com.example.classdesign;

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
        File file = new File("D:\\IDEAProject\\classdesign\\src\\test\\java\\com\\example\\classdesign\\test.txt");
        String name = file.getName();
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        System.out.println(name + ":" + channel.size());
        channel.close();
        fileInputStream.close();
    }

}
