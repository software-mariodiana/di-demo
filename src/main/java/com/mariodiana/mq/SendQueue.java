package com.mariodiana.mq;


public interface SendQueue {
    public void send(Message message);
    public String getTopic();
}
