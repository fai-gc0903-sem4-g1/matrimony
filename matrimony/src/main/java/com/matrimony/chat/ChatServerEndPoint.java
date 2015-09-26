/**
 * 
 */
package com.matrimony.chat;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author SON
 *
 */
@ServerEndpoint("/websocketserver")
public class ChatServerEndPoint {
	private static Set<Session> userOnlineChat=new HashSet<Session>();
	
	public void handleOpen(Session session){
		userOnlineChat.add(session);
		System.out.println(session.getId()+" connected.");
	}
	
	public void handleMessage(String data, Session session){
		System.out.println("Data: "+data);
		
	}
	
	public void handleClose(Session session){
		userOnlineChat.remove(session);
		System.out.println(session.getId()+" closed.");
	}
	
}
