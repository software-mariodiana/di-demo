package com.mariodiana.mq;

import java.util.Properties;
import java.util.function.Function;


public class SendQueueFactory {
    private static Function<Properties, SendQueue> _function = (properties) -> { 
        throw new IllegalStateException("Function not initialized."); 
    };
    
    private static Properties _properties;
            
    public static SendQueue sendQueue() {
        return _function.apply(_properties);
    }
    
    public static void initialize(Function<Properties, SendQueue> f, Properties properties) {
        _function = f;
        _properties = properties;
    }
}
