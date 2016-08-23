package com.mariodiana.mq.kafka;

import java.util.Properties;
import java.util.function.Function;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;


public class ProducerFactory {
    // The default produces a new KafkaProducer instance.
    private static Function<Properties, Producer<String, String>> _function = 
            (properties) -> { return new KafkaProducer<>(properties); };
    
    public static Producer<String, String> createProducer(Properties properties) {
        return _function.apply(properties);
    }
    
    public static void setProducerFunction(Function<Properties, Producer<String, String>> f) {
        _function = f;
    }
}
