package com.mariodiana.mq.kafka;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.Properties;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.mariodiana.mq.Message;
import com.mariodiana.mq.SendQueue;


public class KafkaSendQueueTest {
    @Test
    public void sendMethodShouldSendMessage() {
        Properties properties = new Properties();
        properties.put("com.mariodiana.sendqueue.topic", "test");
        
        // We won't use the Properties here, but the method expects it.
        ProducerFactory.setProducerFunction((props) -> { 
            return new MockProducer<>(true, new StringSerializer(), new StringSerializer()); 
        });
        
        Message message = mock(Message.class);
        
        SendQueue instance = new KafkaSendQueue(properties);
        instance.send(message);
        
        verify(message, times(1)).getId();
        verify(message, times(1)).getData();
    }
    
    @Test
    public void instanceShouldReturnTopic() {
        String expected = "test";
        Properties properties = new Properties();
        properties.put("com.mariodiana.sendqueue.topic", expected);
        
        SendQueue instance = new KafkaSendQueue(properties);
        String result = instance.getTopic();
        
        assertTrue(expected.equals(result));
    }
}
