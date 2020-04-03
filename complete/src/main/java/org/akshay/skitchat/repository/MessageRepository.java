package org.akshay.skitchat.repository;

import java.util.List;

import org.akshay.skitchat.entity.MessageEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MessageRepository extends CrudRepository<MessageEntity, Integer> {

	//@Query("SELECT m FROM MessageEntity m WHERE m.receiverId = 1")
	// List<MessageEntity> findSoulInbox(@param String receiverId);

	@Query(value = "SELECT m FROM MessageEntity m WHERE m.receiverId = :receiverId")
	List<MessageEntity> findSoulInbox(@Param(value = "receiverId") String receiverId);
	
	@Query(value = "SELECT m FROM MessageEntity m WHERE m.senderId = :senderId")
	List<MessageEntity> getSentMessage(@Param(value = "senderId") String senderId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM MessageEntity m  WHERE m.messageId = :messageId")
	void deleteMessage(@Param(value = "messageId") String messageId);
	
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE MessageEntity m SET m.readStatus = :status WHERE m.messageId = :messageId")
	void setRead(@Param(value = "messageId") String messageId, @Param(value = "status") String status);

}