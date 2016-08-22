/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariodiana.mq.kafka;

import java.util.Properties;
import java.util.function.Function;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;


/**
 *
 * @author mario
 */
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
