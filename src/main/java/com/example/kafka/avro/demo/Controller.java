package com.example.kafka.avro.demo;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private final KafkaTemplate<String,Customer> kafkaTemplate;

    @GetMapping("/customers")
    public  String greet(){

        Customer customer = Customer.newBuilder()
                .setAge(23)
                .setWeight(65.3f)
                .setHeight(5.3f)
                .setAutomatedEmail(false)
                .setFirstName("Sourav")
                .setLastName("Pati")
                .build();


        kafkaTemplate.send("avro-test-customer",customer);

        return "Customer information sent to kafka";
    }
}
