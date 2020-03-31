package org.akshay.skitchat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.akshay.skitchat.entity.MessageEntity;



public class InboxData implements Serializable{

	List<MessageEntity> messageList ;

	public InboxData() {
		messageList = new ArrayList<>();
	}

	 

	public List<MessageEntity> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageEntity> messageList) {
		this.messageList = messageList;
	}


}
