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
	}

	@OnMessage
	public void handleMessage(String data, Session session) {
	}

	@OnClose
	public void handleClose(Session session) {
	}

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
}
