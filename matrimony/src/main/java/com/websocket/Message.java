/**
 * 
 */
package com.websocket;

import java.sql.Timestamp;

/**
 * @author SON
 *
 */
public class Message {
	public String senderId;
	public String receiverId;
	public String content;
	public String type;
	public long time;
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Message [senderId=" + senderId + ", receiverId=" + receiverId + ", content=" + content + ", type="
				+ type + ", time=" + time + "]";
	}
	
}
