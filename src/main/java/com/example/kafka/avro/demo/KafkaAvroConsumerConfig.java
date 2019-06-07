package com.example.kafka.avro.demo;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;

import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@ComponentScan("com.example.kafka.avro.demo")
public class KafkaAvroConsumerConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,Customer>> kafkaListenerContainerFactory(){

        ConcurrentKafkaListenerContainerFactory<String,Customer> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConcurrency(3);
        factory.getContainerProperties()
                .setPollTimeout(3000);
        factory.setBatchListener(true);

        return  factory;
    }

    @Bean
    public Map<String,Object> consumerConfig() {

        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties());

        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-3ce1f799-echo-enterprise-data-integration.aivencloud.com:25294");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG,"test-group");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.CLIENT_ID_CONFIG,"well-completion-consumer");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 5000);
        props.put("security.protocol","SSL");

        return  props;

    }

}
