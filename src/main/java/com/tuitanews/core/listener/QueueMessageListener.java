package com.tuitanews.core.listener;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuitanews.domain.NewsBeanVO;
import com.tuitanews.service.NewsBeanService;


public class QueueMessageListener implements MessageListener {
	
	@Autowired
	NewsBeanService newsBeanService;
	
	private final static Logger logger = LoggerFactory.getLogger(QueueMessageListener.class);
    
    public void onMessage(Message m) {     
        try{     
            if(m instanceof TextMessage){ //接收文本消息     
                TextMessage message = (TextMessage)m;     
                System.out.println(message.getText());     
            }else if(m instanceof MapMessage){ //接收键值对消息     
                MapMessage message = (MapMessage)m;     
            }else if(m instanceof StreamMessage){ //接收流消息     
                StreamMessage message = (StreamMessage)m;     
                System.out.println(message.readString());     
                System.out.println(message.readLong());     
            }else if(m instanceof BytesMessage){ //接收字节消息     
                byte[] b = new byte[1024];     
                int len = -1;     
                BytesMessage message = (BytesMessage)m;     
                while((len=message.readBytes(b))!=-1){     
                    System.out.println(new String(b, 0, len));     
                }     
            }else if(m instanceof ObjectMessage){ //接收对象消息     
                ObjectMessage message = (ObjectMessage)m;     
                NewsBeanVO newsBeanVO = (NewsBeanVO)message.getObject();     
                //System.out.println("消息监听到对象====>>新闻标题：" + newsBeanVO.getTitle());
                //logger.info(newsBeanVO.getTitle());
                newsBeanService.insertNewsBeanByVo(newsBeanVO);
            }else{     
                System.out.println(m);
            }     
                 
        }catch(JMSException e){     
            e.printStackTrace();     
        }     
    }     

}