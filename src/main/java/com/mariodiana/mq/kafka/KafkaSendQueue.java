package com.mariodiana.mq.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.mariodiana.mq.Message;
import com.mariodiana.mq.SendQueue;


public class KafkaSendQueue implements SendQueue {
    private final Properties _properties;
    
    public KafkaSendQueue(Properties properties) {
        _properties = properties;
    }

    @Override
    public void send(Message message) {
        try (Producer<String, String> producer = createProducer()) {
            producer.send(createProducerRecord(message));
        }
    }
    
    @Override
    public String getTopic() {
        return getProperties().getProperty("com.mariodiana.sendqueue.topic");
    }
    
    private Producer<String, String> createProducer() {
        return ProducerFactory.createProducer(getProperties());
    }
    
    private Properties getProperties() {
        return _properties;
    }
    
    private ProducerRecord<String, String> createProducerRecord(Message message) {
        return new ProducerRecord<>(getTopic(), message.getId(), message.getData());
    }
}
