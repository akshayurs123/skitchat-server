package org.akshay.skitchat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

@Entity
public class MessageEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  long id;
	 
	private String messageId;
	private String senderId;
	private String receiverId;
	private String senderUsername;
	private String receiverUsername;
	private String date;
	private String readStatus;
	private String messageBody;
	private String messageSubject;
	private String tag;
	private String fu1;
	private String fu2;
	private String fu3;



	public MessageEntity() {}


	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
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
	
	
	public String getSenderUsername() {
		return senderUsername;
	}


	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}


	public String getReceiverUsername() {
		return receiverUsername;
	}


	public void setReceiverUsername(String receiverUsername) {
		this.receiverUsername = receiverUsername;
	}


	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public String getMessageSubject() {
		return messageSubject;
	}
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getFu1() {
		return fu1;
	}
	public void setFu1(String fu1) {
		this.fu1 = fu1;
	}
	public String getFu2() {
		return fu2;
	}
	public void setFu2(String fu2) {
		this.fu2 = fu2;
	}
	public String getFu3() {
		return fu3;
	}
	public void setFu3(String fu3) {
		this.fu3 = fu3;
	}






}
