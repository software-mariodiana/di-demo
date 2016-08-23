/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariodiana.mq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mario
 */
public class DispatcherTest {
    private SendQueue _sendQueue = null;
    
    @Before
    public void setUp() {
        _sendQueue = mock(SendQueue.class);
        SendQueueFactory.initialize((props) -> { return _sendQueue; }, null);
    }
    
    @After
    public void tearDown() {
        _sendQueue = null;
    }
    
    @Test
    public void messageShouldBeAddedToQueue() {
        Message mock = mock(Message.class);
        when(mock.getId()).thenReturn("42");
        Dispatcher.INSTANCE.sendMessageForProcessing(mock);
        verify(mock, times(1)).getId();
        assertTrue(Dispatcher.INSTANCE.getMessageIds().size() == 1);
    }
    
    @Test
    public void messageShouldBeUnregisteredAfterProcessing() {
        Message mock = mock(Message.class);
        when(mock.getId()).thenReturn("42");
        Dispatcher.INSTANCE.sendMessageForProcessing(mock);
        List<Message> processed = new ArrayList<>();
        processed.add(mock);
        Dispatcher.INSTANCE.pickupProcessedMessages(processed);
        assertTrue(Dispatcher.INSTANCE.getMessageIds().isEmpty());
    }
}
