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
public class ChatEndPoint {
	private static Set<Session> userOnlineChat = new HashSet<Session>();

	@OnOpen
	public void handleOpen(Session session) {
		userOnlineChat.add(session);
		System.out.println(session.getId() + " connected.");
	}

	@OnMessage
	public void handleMessage(String data, Session session) {
		Message msg = Global.gson.fromJson(data, Message.class);
		String userId = (String) session.getUserProperties().get("userId");
		System.out.println("Data: " + msg.toString());
		if (userId == null) {
			session.getUserProperties().put("userId", msg.getSenderId());
		} else {
			System.out.println(userId);
			Session receiverSession = findUserInSession(msg.getReceiverId());
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

	}

	@OnClose
	public void handleClose(Session session) {
		userOnlineChat.remove(session);
		System.out.println(session.getId() + " closed.");
	}

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}

	public Session findUserInSession(String userId) {
		Session userSession = null;
		for (Session ss : userOnlineChat) {
			if (userId.equals(ss.getUserProperties().get("userId"))) {
				userSession = ss;
				break;
			}
		}
		return userSession;
	}
}
