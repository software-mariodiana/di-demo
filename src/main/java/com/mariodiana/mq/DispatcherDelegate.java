package com.mariodiana.mq;

import java.util.List;
import java.util.Map;


public abstract class DispatcherDelegate {
    protected Map<String, Message> _map;
    
    /**
     * Process list of Message objects.
     * 
     * @param messages java.util.List of com.mariodiana.mq.Message objects.
     */
    public abstract void processMessages(List<Message> messages);
    
    public void setMap(Map<String, Message> map) {
        _map = map;
    }
    
    public Map<String, Message> getMap() {
        return _map;
    }
}
