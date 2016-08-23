package com.mariodiana.mq;

import java.util.List;
import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;


public enum Dispatcher {
    INSTANCE;
    
    private final Map<String, Message> _messages = new ConcurrentHashMap<>();
    
    private DispatcherDelegate _delegate;
    
    public void setDelegate(DispatcherDelegate delegate) {
        delegate.setMap(_messages);
        _delegate = delegate;
    }
    
    public DispatcherDelegate getDelegate() {
        return _delegate;
    }
    
    public void sendMessageForProcessing(Message message) {
        registerMessage(message);
        sendMessage(message);
    }
    
    public void pickupProcessedMessages(List<Message> messages) {
        if (getDelegate() == null) {
            messages.forEach((m) -> { m.setProcessedData(""); _messages.remove(m.getId()); });
        }
        else {
            getDelegate().processMessages(messages);
        }
    }
    
    protected List<String> getMessageIds() {
        return new java.util.ArrayList<>(_messages.keySet());
    }
    
    private void registerMessage(Message message) {
        _messages.put(message.getId(), message);
    }
    
    private void sendMessage(Message message) {
        SendQueue queue = SendQueueFactory.sendQueue();
        queue.send(message);
    }
}
