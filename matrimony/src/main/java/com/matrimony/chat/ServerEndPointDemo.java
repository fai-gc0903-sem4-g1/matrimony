/**
 * 
 */
package com.matrimony.chat;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

/**
 * @author SON
 *
 */
@ServerEndpoint("/serverendpointdemo")
public class ServerEndPointDemo {
	@OnOpen
	public void handleOpend() {
		System.out.println("Client is now connected");
	}

	@OnMessage
	public String handleMessage(String msg) {
		System.out.println("receiver from client: " + msg);
		String replyMsg = "echo " + msg;
		System.out.println("send to client: " + replyMsg);
		return replyMsg;
	}

	@OnClose
	public void handleClose() {
		System.out.println("client is disconnected");
	}

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
}
