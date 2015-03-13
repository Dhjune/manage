package com.fanfan.manage.control.book;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fanfan.manage.modle.book.Book;


@ServerEndpoint(value="/test/websoc")
public class WebSocketTest {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
    private JsonGenerator jsonGenerator = null;
    
    private StringWriter sw = new StringWriter();
	
	
	@OnMessage
	public void handleMessage(String message,Session session) throws IOException {
		System.out.println("the message is "+message);
		Book book = new Book();
		Set<Session> set=session.getOpenSessions();
	    Iterator<Session> it=set.iterator();
	  //迭代遍历  
	    while(it.hasNext()){
	        Session everySession=it.next();
	        if(everySession.isOpen()){
	            everySession.getBasicRemote().sendText("ssssssssssssss");
	        }
	    }
		book.setBookTitle("sdfasgjasgf");
		try {
			objectMapper.writeValue(sw,book);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//jsonGenerator.writeObject(croot);
		String str = sw.toString();
	   // return str;
//		return book;
	}
	
	
	@OnOpen
	public void onOpen() {
	    System.out.println("Client connected");
	}
	 
    @OnClose
    public void onClose() {
    	System.out.println("Connection closed");
    }

}
