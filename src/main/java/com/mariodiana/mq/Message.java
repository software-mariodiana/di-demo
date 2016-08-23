package com.mariodiana.mq;


public interface Message {
    public String getId();
    public String getData();
    public void setProcessedData(String data);
    public void getProcessedData();
}
