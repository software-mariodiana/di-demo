/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariodiana.mq;

/**
 *
 * @author mario
 */
public interface SendQueue {
    public void send(Message message);
    public String getTopic();
}
