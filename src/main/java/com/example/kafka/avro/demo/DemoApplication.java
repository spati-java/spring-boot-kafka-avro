package com.example.kafka.avro.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import org.springframework.util.FileCopyUtils;

import java.io.FileOutputStream;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws Exception {

        FileCopyUtils.copy(new ClassPathResource("client.truststore.jks").getInputStream(), new FileOutputStream("/tmp/client.truststore.jks"));

        FileCopyUtils.copy(new ClassPathResource("client.keystore.p12").getInputStream(), new FileOutputStream("/tmp/client.keystore.p12"));

        SpringApplication.run(DemoApplication.class, args);

    }


}
