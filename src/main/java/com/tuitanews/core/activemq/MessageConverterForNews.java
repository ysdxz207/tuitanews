package com.tuitanews.core.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class MessageConverterForNews implements MessageConverter {  
    /* (non-Javadoc) 
     * @see org.springframework.jms.support.converter.MessageConverter#toMessage(java.lang.Object, javax.jms.Session) 
     */  
    public Message toMessage(Object object, Session session)  
            throws JMSException, MessageConversionException {  
        System.out.println("[toMessage]");  
        ObjectMessage objectMessage = session.createObjectMessage();  
          
        objectMessage.setJMSExpiration(1000);  
        objectMessage.setStringProperty("key_news", object+"_add");  
          
        return objectMessage;  
    }  
    /* (non-Javadoc) 
     * @see org.springframework.jms.support.converter.MessageConverter#fromMessage(javax.jms.Message) 
     */  
    public Object fromMessage(Message message) throws JMSException,  
            MessageConversionException {  
        System.out.println("[fromMessage]");  
        ObjectMessage objectMessage = (ObjectMessage) message;  
          
        return objectMessage.getObjectProperty("key_news");  
    }  
}  