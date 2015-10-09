/**
 * 
 */
package com.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.matrimony.util.Global;

/**
 * @author SON
 *
 */
@ServerEndpoint("/chatserver")
public class TextChatWSocket {

	@OnOpen
	public void handleOpen(Session session) {
		
	}

	@OnMessage
	public void handleMessage(String data, Session session) {
		Message msg = Global.gson.fromJson(data, Message.class);
		System.out.println("Data: " + msg.toString());
		
			Session receiverSession = getSessionByUserIdIfOnline(msg.getReceiverId());
			if (receiverSession == null) {
				System.out.println("nguoi nay offline");
			} else {
				try {
					receiverSession.getBasicRemote().sendText(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

	@OnClose
	public void handleClose(Session session){
	}

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}

	public Session getSessionByUserIdIfOnline(String userId) {
		Session userSession = null;
		for (Session ss : GlobalWSocket.userOnline) {
			if (userId.equals(ss.getUserProperties().get("id"))) {
				userSession = ss;
				break;
			}
		}
		return userSession;
	}
}
