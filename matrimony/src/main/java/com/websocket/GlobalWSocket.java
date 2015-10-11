/**
 * 
 */
package com.websocket;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author SON
 *
 */
@ServerEndpoint("/globalwsocket")
public class GlobalWSocket {
	public static Set<Session> userOnline = new HashSet<Session>();

	@OnOpen
	public void handleOpen(Session session) {
		System.out.println("global wsocket accept client");
	}

	@OnMessage
	public void handleMessage(String data, Session session) {
		session.getUserProperties().put("id", data);
		userOnline.add(session);
		System.out.println(data + " online now");
	}

	@OnClose
	public void handleClose(Session session) {
		userOnline.remove(session);
		System.out.println(session.getUserProperties().get("id") + " offline");
	}

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
}
